package fpoly.acount.ph56623_thi19.Demo2;

public class Todo {
    private int id,status;
    private  String title,content,data,type;

    public Todo(int id, int status, String title, String content, String data, String type) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.content = content;
        this.data = data;
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
