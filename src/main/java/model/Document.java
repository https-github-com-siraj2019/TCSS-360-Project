package main.java.model;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;

public class Document extends FileEntity {

    private File file;
    private HashSet<Tag> tags;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = Objects.requireNonNull(file);
    }

    public HashSet<Tag> getTags() {
        return tags;
    }

    public void setTags(HashSet<Tag> tags) {
        this.tags = Objects.requireNonNull(tags);
    }

    public void addTag(Tag tag) {
        this.tags.add(Objects.requireNonNull(tag));
    }

    public void removeTag(Tag tag) {
        this.tags.remove(Objects.requireNonNull(tag));
    }


}
