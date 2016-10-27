package com.app.server.businessservice.appbasicsetup.userrolemanagement;
import com.app.server.repository.appbasicsetup.userrolemanagement.MenuRepository;

import com.app.shared.appbasicsetup.userrolemanagement.MenuTreee;

import com.app.shared.appbasicsetup.userrolemanagement.Menu;

import com.app.server.repository.appbasicsetup.aaa.ArtUserStatusRepository;

import com.app.shared.appbasicsetup.aaa.ArtUserLastStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

@Service
public class MenuBusinessServiceImpl implements MenuBusinessService {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	ArtUserStatusRepository artUserStatusRepository;

	/**
	 * getUserMenu function accept the userId and appType and return the String
	 * of jsonArray containing list of Menus initialize the getUserMenu with
	 * given configuration
	 * 
	 * @param : userId, appType
	 * @returns String
	 * @throws : Exception
	 */
	public String getUserMenu(final String userId, final Integer appType) throws Exception {
		JSONArray json = new JSONArray();
		final List<String> headers = menuRepository.getHeaderByUser(userId, appType);
		for (Iterator<String> iterator = headers.iterator(); iterator.hasNext();) {
			final String menuTreeId = (String) iterator.next();
			final List<Object[]> subMenu = menuRepository.getSubMenus(menuTreeId, userId, appType);
			List<Menu> menus = new ArrayList<Menu>();
			for (Object[] subMenus : subMenu) {
				Menu menu = new Menu((String) subMenus[0], (String) subMenus[1], (String) subMenus[2], (String) subMenus[3], (String) subMenus[4], (String) subMenus[5],
						(String) subMenus[2], (String) subMenus[6], (String) subMenus[7], (boolean) subMenus[8]);
				menus.add(menu);
			}
			try {
				json.add(new MenuTreee(menus).createMenuTree());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json.toString();
	}

	/**
	 * storeMenu function accept userId, menuId and json string to store menu
	 * initialize the storeMenu with given configuration
	 * 
	 * @param : userId, menuId, json
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	public void storeMenu(final String userId, final String menuId, final String json) throws Exception {
		final ArtUserLastStatus artUserLastStatus = new ArtUserLastStatus(userId, menuId, json);
		artUserStatusRepository.save(artUserLastStatus);
	}

	/**
	 * fetchStoreMenus accept userId and return the list of menus respect to
	 * userId initialize the fetchStoreMenus with given configuration
	 * 
	 * @param : userId
	 * @returns String
	 * @throws : Exception
	 */
	@Override
	public String fetchStoreMenus(final String userId) throws Exception {
		final List<ArtUserLastStatus> artUserLastStatus = artUserStatusRepository.findByUserId(userId);
		JSONArray array = new JSONArray();
		for (Iterator iterator = artUserLastStatus.iterator(); iterator.hasNext();) {
			ArtUserLastStatus object = (ArtUserLastStatus) iterator.next();
			array.add(object.toJSON());
		}
		return array.toString();
	}

	/**
	 * deleteMenu accept the userId and menuId to delete menu from list
	 * initialize the delete with given configuration
	 * 
	 * @param : userId, menuId
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	public void deleteMenu(final String userId, final String menuId) throws Exception {
		List<ArtUserLastStatus> list = artUserStatusRepository.findByUserMenuId(userId, menuId);

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			final ArtUserLastStatus artUserLastStatus = (ArtUserLastStatus) iterator.next();
			artUserStatusRepository.delete(artUserLastStatus.getId());

		}
	}

	/**
	 * findMainScreenMenus function accept userId and appType to find and return
	 * mainscreen menus initialize the findMainScreenMenus with given
	 * configuration
	 * 
	 * @param : userId, appType
	 * @returns String
	 * @throws : Exception
	 */
	@Override
	public String findMainScreenMenus(final String userId, final Integer appType) throws Exception {
		JSONObject mainPageMenus = new JSONObject();

		/** PREPARING HOME PAGES */
		JSONArray homeScreenMenus = new JSONArray();

		/** PREPARING MENU TREE ITEMS */
		JSONArray menusTreeJSONArray = new JSONArray();
		final List<String> headers = menuRepository.getHeaderByUser(userId, appType);
		for (Iterator<String> iterator = headers.iterator(); iterator.hasNext();) {
			String menuTreeId = (String) iterator.next();
			List<Object[]> subMenu = menuRepository.getSubMenus(menuTreeId, userId, appType);
			List<Menu> menus = new ArrayList<Menu>();
			for (Object[] subMenus : subMenu) {
				Menu menu = new Menu((String) subMenus[0], (String) subMenus[1], (String) subMenus[2], (String) subMenus[3], (String) subMenus[4], (String) subMenus[5],
						(String) subMenus[2], (String) subMenus[6], (String) subMenus[7], (boolean) subMenus[8]);
				menus.add(menu);

				/** PREPARING HOME SCREENS */
				if (menu.getMenuCommands() != null && !menu.getMenuCommands().isEmpty() && !menu.getMenuCommands().equals(" ")) {
					try {
						JSONObject menuCommandJSON = new JSONObject(menu.getMenuCommands());
						if (menuCommandJSON.has("homeScreen") && menuCommandJSON.getBoolean("homeScreen") == true) {
							homeScreenMenus.add(menu.toJSON());
						}
					} catch (Exception e) {
						/** skipping exception when menu command isn't a json */
					}
				}
			}
			try {
				menusTreeJSONArray.add(new MenuTreee(menus).createMenuTree());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		mainPageMenus.put("menus", menusTreeJSONArray);
		if (!homeScreenMenus.isEmpty()) {
			mainPageMenus.put("homeScreenMenus", homeScreenMenus);
		}
		return mainPageMenus.toString();
	}
}
