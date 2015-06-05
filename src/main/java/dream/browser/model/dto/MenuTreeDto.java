package dream.browser.model.dto;

import java.util.List;

import dream.browser.model.Menu;

public class MenuTreeDto extends Menu{
	
	private static final long serialVersionUID = 1L;

	private String text;						//菜单显示的名称
	
	private List<MenuTreeDto> children;			//子菜单
	
	private String state="closed";						//菜单状态。若其下有子菜单，则此状态有效，可为closed或open

	public List<MenuTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTreeDto> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
