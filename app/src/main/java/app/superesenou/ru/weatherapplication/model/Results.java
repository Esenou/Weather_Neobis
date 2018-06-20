package app.superesenou.ru.weatherapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {
    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("created")
    @Expose
    private  String created;

    @SerializedName("lang")
    @Expose
    private  String lang;

    @SerializedName("results")
    @Expose
    protected Query results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Query getResults() {
        return results;
    }

    public void setResults(Query results) {
        this.results = results;
    }
}
