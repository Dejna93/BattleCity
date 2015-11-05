package Utils;

public enum ScoreType {
	 
    PLAYER(50), 
    MAP(10);

    int score;

    private ScoreType(int score) {
        this.score=  score;
    }

    @Override
    public String toString() {
        String poprzedniaNazwa = super.toString();
        String nowaNazwa = poprzedniaNazwa.toLowerCase();
        return nowaNazwa;
    }

 
}