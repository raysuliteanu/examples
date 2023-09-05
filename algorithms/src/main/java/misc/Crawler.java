package misc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import lombok.extern.java.Log;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Log
public class Crawler {
    public Set<String> uniqueHosts(final String startUrl) {
        final ConcurrentSkipListSet<String> hosts = new ConcurrentSkipListSet<>();
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new CrawlerTask(startUrl, hosts));

    }

    private static class CrawlerTask extends RecursiveTask<Set<String>> {
        private final Connection connection;
        private final ConcurrentSkipListSet<String> hosts;

        public CrawlerTask(String startUrl, final ConcurrentSkipListSet<String> hosts) {
            connection = Jsoup.connect(startUrl);
            this.hosts = hosts;
        }

        @Override
        protected Set<String> compute() {
            final List<ForkJoinTask<Set<String>>> tasks = new LinkedList<>();
            try {
                Document document = connection.get();
                Elements links = document.select("a[href]");
                links.forEach(element -> {
                    String href = element.attributes().get("href");
                    if (href.startsWith("http")) {
                        try {
                            URL url = new URL(href);
                            String host = url.getHost();
                            synchronized (hosts) {
                                if (!hosts.contains(host)) {
                                    hosts.add(host);
                                    String target = url.getProtocol() + "://" + host;
                                    CrawlerTask crawlerTask = new CrawlerTask(target, hosts);
                                    tasks.add(crawlerTask.fork());
                                }
                            }
                        } catch (MalformedURLException e) {
                            log.warning(e.getMessage());
                        }
                    }
                });
            } catch (HttpStatusException e) {
                log.warning(e.toString());
            } catch (IOException e) {
                log.warning(e.getMessage());
            }
            tasks.forEach(ForkJoinTask::join);
            return hosts;
        }
    }
}
