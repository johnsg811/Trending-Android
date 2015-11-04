package msd.com.trending;

/**
 * Created by Johns on 04/11/2015.
 */
public class Data {

    private String website;
    private String webUrl;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Data(String website, String webUrl)
    {
        this.website = website;
        this.webUrl = webUrl;

    }



}
