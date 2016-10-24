package lambdas.filereader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedReaderRunner {
    private static final Logger LOG = LoggerFactory.getLogger(BufferedReaderRunner.class);

    public static void main(String[] args) throws IOException {
        BufferedReaderRunner bufferedReaderRunner = new BufferedReaderRunner();
        bufferedReaderRunner.demo();
    }

    private void demo() throws IOException {
        processSingleLine();
        processTwoLines();
    }

    private void processSingleLine() throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        LOG.info("Read one line: {}", oneLine);
    }

    private void processTwoLines() throws IOException {
        String twoLines = processFile((BufferedReader br) -> br.readLine() + " : " + br.readLine());
        LOG.info("read TwoLines: {}", twoLines);
    }

    /**
     * This is the boiler plate code, that will accept a lambda to do the actualy work
     * @param bufferedReaderProcessor
     * @return
     * @throws IOException
     */
    private String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {
        try (InputStream is = getClass().getResourceAsStream("/simpleTextFile.txt");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReaderProcessor.process(bufferedReader);
        }
    }
}
