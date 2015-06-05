package dream.browser.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private String id;

    private String name;

    private String url;

    private String parentId;

    private Integer listOrder;

    private Boolean isSoftDelete;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public Boolean getIsSoftDelete() {
        return isSoftDelete;
    }

    public void setIsSoftDelete(Boolean isSoftDelete) {
        this.isSoftDelete = isSoftDelete;
    }
}