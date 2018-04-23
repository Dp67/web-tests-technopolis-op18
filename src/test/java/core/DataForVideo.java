package core;

public enum DataForVideo {
    NAME("VideoTest"),
    DURATION("05:33") {},
    DESCRIPTION(""){},
    VIEWS("0"){},
    TAGS("") {},
    ERROR("загрузить видео невозможно, попробуйте еще раз");
    private final String claim;
    DataForVideo(String claim) {
        this.claim = claim;
    }
    public String getClaim() {
        return claim;
    }
}