import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.shaded.com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedCounterExample implements SharedCountListener {
    private static final int CLIENT_COUNT = 5;
    private static final String PATH = "/examples/counter";


    // 会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    // Zookeeper服务地址
    private static final String CONNECT_ADDR = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        final Random rand = new Random();
        SharedCounterExample example = new SharedCounterExample();
        try{
          //  CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(CONNECT_ADDR)
                    .connectionTimeoutMs(CONNECTION_TIMEOUT)
                    .sessionTimeoutMs(SESSION_TIMEOUT)
                    .retryPolicy(retryPolicy)
                    .build();
            client.start();

            SharedCount baseCount = new SharedCount(client, PATH, 0);
            baseCount.addListener(example);
            baseCount.start();

            List<SharedCount> examples = Lists.newArrayList();
            ExecutorService service = Executors.newFixedThreadPool(CLIENT_COUNT);
            for (int i = 0; i < CLIENT_COUNT; i++) {


                final SharedCount count = new SharedCount(client, PATH, 0);
                examples.add(count);
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        count.start();
                        Thread.sleep(rand.nextInt(10000));
                        int add = count.getCount() + rand.nextInt(10);
                        System.out.println("要更改的值为: "+add);
                        boolean b = count.trySetCount(count.getVersionedValue(), add);
                        System.out.println("更改结果为: " + b);
                        return null;
                    }
                };
                service.submit(task);
            }

            service.shutdown();
            service.awaitTermination(50, TimeUnit.MINUTES);


            for (int i = 0; i < CLIENT_COUNT; i++) {
                examples.get(i).close();
            }
            baseCount.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCountReader, int i) throws Exception {

    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

    }
}
