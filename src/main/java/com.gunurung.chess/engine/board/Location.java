package com.gunurung.chess.engine.board;

import com.gunurung.chess.engine.logger.Tokenable;

public class Location implements Tokenable {
    final byte file;
    final byte rank;
    public Location(int file, int rank){
        if(file >= 'a') file += 1 - 'a';
        this.file = (byte)file;
        this.rank = (byte)rank;
    }
    public Location offset(int file, int rank){
        return new Location(this.file + file, this.rank + rank);
    }
    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location){
            return ((Location) obj).file == file && ((Location) obj).rank == rank;
        }else return false;
    }

    public String toString(){
        return token().toString();
    }
    @Override
    public StringBuilder token() {
        return new StringBuilder((char)(file+'a')).append(rank);
    }
}
