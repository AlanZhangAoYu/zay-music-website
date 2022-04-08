package util;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import java.io.File;
import java.util.HashMap;

/**
 * @author ZAY
 */
public class Mp3Util {
    public static HashMap<String,String> getMp3Info(File file) throws Exception{
        MP3File mp3File = (MP3File) AudioFileIO.read(file);
        AbstractID3v2Tag abstractId3v2Tag = mp3File.getID3v2Tag();
        MP3AudioHeader mp3AudioHeader = mp3File.getMP3AudioHeader();

        String songName = abstractId3v2Tag.getFirst(FieldKey.TITLE);
        String artist = abstractId3v2Tag.getFirst(FieldKey.ARTIST);
        String album = abstractId3v2Tag.getFirst(FieldKey.ALBUM);
        int length = mp3AudioHeader.getTrackLength();

        HashMap<String,String> map=new HashMap<>(4);
        map.put("songName",songName);
        map.put("artist",artist);
        map.put("album",album);
        map.put("length",length / 60+":"+(length % 60 < 10 ? "0"+length % 60 : length % 60));
        return map;
    }
}
