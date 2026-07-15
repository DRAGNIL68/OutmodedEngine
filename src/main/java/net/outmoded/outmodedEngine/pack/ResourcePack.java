package net.outmoded.outmodedEngine.pack;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import net.outmoded.outmodedEngine.pack.jsonObjects.Writable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import java.util.Base64;

public class ResourcePack {
    FileSystem fileSystem;
    private final String name;

    public ResourcePack(String name){
        fileSystem = Jimfs.newFileSystem(Configuration.unix());
        this.name = name;
        createPath("/assets");


    }
    public String getName(){ return name; }

    public boolean copyFileFromResources(InputStream inputStream, String pastePath) { // use something like this as input: InputStream inputStream = MyPlugin.getInstance().getResource("plugin.yml");
        try {

            Path directory = fileSystem.getPath(pastePath); // gets path in virtual file system
            if (inputStream == null){
                return false;
            }

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Files.copy(inputStream, directory, StandardCopyOption.REPLACE_EXISTING);

            return true;

        } catch (IOException e) {
            throw new RuntimeException("failed to copy file from resources");

        }
    }

    public void copyFileFromDisk(String filePath, String pastePath) {
        try {
            Path directory = fileSystem.getPath(pastePath);
            Path path = Paths.get(filePath);

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Files.copy(path, directory, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }


    public void createPath(String path){
        try {
            Path directory = fileSystem.getPath(path);
            Files.createDirectories(directory);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createGenericFile(String fileName, String path, String contents){
        try {
            Path directory = fileSystem.getPath(path);

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }



            Path file = directory.resolve(fileName);

            Files.write(file, ImmutableList.of(contents), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void base64ToTexture(String fileName, String path, String textureAsBase64 ) {
        try {
            Path directory = fileSystem.getPath(path);

            byte[] decodedBytes = Base64.getDecoder().decode(textureAsBase64);


            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            Path file = directory.resolve(fileName);

            Files.write(file, decodedBytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean hasFile(String filePath){
        Path directory = fileSystem.getPath(filePath);
        return Files.exists(directory);
    }

    public void writeJsonObject(Writable object, String writePath){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createGenericFile(object.getFileName(), writePath, objectMapper.writeValueAsString(object));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void build(String outputFilePath) {
        try {
            ZipFileUtil zipFileUtil = new ZipFileUtil(outputFilePath, fileSystem);
            zipFileUtil.addToZip("");

            if (!hasFile("pack.mcmeta")){
                throw new RuntimeException("Resource pack dose not have pack.mcmeta!");
            };
            zipFileUtil.endZip();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }








}
