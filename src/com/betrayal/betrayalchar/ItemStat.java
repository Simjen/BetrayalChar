package com.betrayal.betrayalchar;

class ItemStat {
    public int might;
    public int speed;
    public int sanity;
    public int knowledge;

    public ItemStat(int might, int speed, int sanity, int knowledge) {
        this.might = might;
        this.speed = speed;
        this.sanity = sanity;
        this.knowledge = knowledge;
    }

    static ItemStat noStat() {
        return new ItemStat(0, 0, 0, 0);
    }
}
