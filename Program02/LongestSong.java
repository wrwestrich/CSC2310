

public class LongestSong implements Command{

    Song song;
    int length;

    public LongestSong(){
        song = null;
        length = 0;
    }

    public void execute(Object node){

        CD cd = (CD)node;

        for(int i = 0; i < cd.getNumberOfTracks(); ++i){
            if(cd.getSong(i).getLength() > length){
                song = cd.getSong(i);
                length = cd.getSong(i).getLength();
            }
        }
    }

    public Song getLongestSong(){
        return song;
    }
}
