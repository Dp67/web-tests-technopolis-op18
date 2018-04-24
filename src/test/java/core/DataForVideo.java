package core;

public enum DataForVideo {
    PATH("C:\\Users\\Eugene\\Desktop\\videoplayback.webm"),
    NAME("VideoTest"),
    DURATION("01:06"),
    DESCRIPTION(""),
    VIEWS("0"),
    TAGS(""),
    ERROR("загрузить видео невозможно, попробуйте еще раз");
    private final String claim;
    DataForVideo(String claim) {
        this.claim = claim;
    }
    public String getClaim() {
        return claim;
    }
}
