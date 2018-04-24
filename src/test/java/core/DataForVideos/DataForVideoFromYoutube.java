package core.DataForVideos;

public enum DataForVideoFromYoutube {
    URL("https://www.youtube.com/watch?v=BcsfftwLUf0&list=RDMMBcsfftwLUf0"),
    NAME("Theory of a Deadman - Santa Monica [OFFICIAL VIDEO]") {},
    DURATION("04:07"){};
    private final String claim;
    DataForVideoFromYoutube(String claim) {
        this.claim = claim;
    }
    public String getClaim() {
        return claim;
    }
}