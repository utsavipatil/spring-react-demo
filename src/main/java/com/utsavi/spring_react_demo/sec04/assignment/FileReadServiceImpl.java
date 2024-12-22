package com.utsavi.spring_react_demo.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReadServiceImpl implements FileReaderService{
    public static final Logger log = LoggerFactory.getLogger(FileReadServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                ()-> openFile(path),
                (reader, sink) -> readFile(reader, sink),
                reader -> closeFile(reader)
        );
    }
    private BufferedReader openFile(Path path) throws IOException {
        log.info("opening a file");
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader bufferedReader, SynchronousSink<String> sink){
        try{
            var line = bufferedReader.readLine();
            log.info("reading line: {}", line);
            if(Objects.isNull(line)){
                sink.complete();
            }else{
                sink.next(line);
            }
        }catch(Exception e){
            sink.error(new RuntimeException(e));
        }
        return bufferedReader;
    }

    private void closeFile(BufferedReader bufferedReader) {
        try{
            bufferedReader.close();
            log.info("File closed");
        }catch(Exception exception){
            throw new RuntimeException(exception);
        }

    }

//    @Override
//    public Flux<String> read(Path path) {
//        return Flux.generate(synchrnounsSink -> {
//            try {
//                synchrnounsSink.next(Files.readString(PATH.resolve(path)));
//                synchrnounsSink.complete();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }

}
