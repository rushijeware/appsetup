package com.app.shared.appbasicsetup.userrolemanagement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

public class Menu {
	private String menuId;
	private String menuTreeId;
	private String menuName;
	private String menuAction;
	private String menuIcon;
	private String userId;
	private String menuLabel;
	private String menuCommands;
	private String refId;
	private boolean autoSave;

	private Map<String, String> map = new HashMap<String, String>();

	public Menu() {
		super();
	}

	public Menu(final Long String, final String menuTreeId, final String menuName, final String menuAction) {
		super();
		this.menuTreeId = menuTreeId;
		this.menuName = menuName;
		this.menuAction = menuAction;
	}

	public Menu(final String menuId, final String menuTreeId, final String menuName, final String menuAction, final String menuIcon, final String userId, final String menuLabel,
			final String menuCommands, final String refId, final boolean autoSave) {
		super();
		this.menuId = menuId;
		this.menuTreeId = menuTreeId;
		this.menuName = menuName;
		this.menuAction = menuAction;
		this.menuIcon = menuIcon;
		this.userId = userId;
		this.menuLabel = menuLabel;
		this.menuCommands = menuCommands;
		this.refId = refId;
		this.autoSave = autoSave;
		createMenuCommand(this.menuCommands);
	}

	@Override
	public String toString() {
		return menuId + " " + menuName;
	}

	public String getMenuTreeId() {
		return menuTreeId;
	}

	public void setMenuTreeId(final String menuTreeId) {
		this.menuTreeId = menuTreeId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(final String menuName) {
		this.menuName = menuName;
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(final String menuAction) {
		this.menuAction = menuAction;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public String getMenuCommands() {
		return menuCommands;
	}

	public void setMenuCommands(String menuCommands) {
		this.menuCommands = menuCommands;
		this.createMenuCommand(menuCommands);
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * createMenuCommand function accept menuCommand initialize the
	 * createMenuCommand with given configuration
	 * 
	 * @param : menuCommand
	 */
	private void createMenuCommand(final String menuCommand) {
		if (menuCommand != null && !menuCommand.isEmpty() && !menuCommand.equals(" ")) {
			final StringTokenizer token = new StringTokenizer(menuCommand, "#");
			while (token.hasMoreElements()) {
				final String command = (String) token.nextElement();

				final StringTokenizer keyValue = new StringTokenizer(command, "=");

				final String key = (keyValue.hasMoreElements()) ? (String) keyValue.nextElement() : "";
				final String value = (keyValue.hasMoreElements()) ? (String) keyValue.nextElement() : "";

				map.put(key.trim(), value.trim());

			}
		}
	}

	public String getCommandDetails(String key) {
		return map.get(key);
	}

	public static void main(String[] args) {
		new Menu().createMenuCommand("split = true#");
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();

		json.put("menuId", menuId);
		json.put("menuTreeId", menuTreeId);
		json.put("menuName", menuName);
		json.put("menuAction", menuAction);
		json.put("menuIcon", menuIcon);
		json.put("userId", userId);
		json.put("menuLabel", menuLabel);
		json.put("text", menuLabel);
		json.put("refId", refId);
		json.put("autoSave", autoSave);
		json.put("menuCommands", menuCommands);

		return json;
	}
}
