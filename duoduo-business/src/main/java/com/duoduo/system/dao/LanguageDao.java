package com.duoduo.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.duoduo.core.dao.BaseDao;
import com.duoduo.core.vo.Page;
import com.duoduo.system.model.Language;

/**
 * 国际化方案语言包数据库处理类
 * @author chengesheng
 * @date 2014-3-17 上午10:47:40
 */
@Repository("languageDao")
public class LanguageDao extends BaseDao {

	private static final RowMapper<Language> entityRowMapper = new RowMapper<Language>() {

		@Override
		public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
			final Language entity = new Language();
			entity.setId(rs.getLong("id"));
			entity.setName(rs.getString("name"));
			entity.setI18nTag(rs.getString("i18n_tag"));
			entity.setOrderIndex(rs.getInt("order_index"));
			entity.setEnable(rs.getInt("enable"));
			entity.setMemo(rs.getString("memo"));
			entity.setCreateTime(rs.getTimestamp("create_time"));
			entity.setUpdateTime(rs.getTimestamp("update_time"));
			return entity;
		}
	};

	private static final String getByIdSql = "select * from sys_language where id=?";

	/**
	 * 根据Id获取
	 */
	public Language getById(String id) {
		try {
			Language language = super.getJdbcTemplate().queryForObject(getByIdSql, entityRowMapper, id);
			return language;
		} catch (DataAccessException e) {
		}
		return null;
	}

	private static final String getByKeySql = "select * from sys_language where `name`=?";

	/**
	 * 根据name获取
	 */
	public Language getByName(String name) {
		try {
			Language language = super.getJdbcTemplate().queryForObject(getByKeySql, entityRowMapper, name);
			return language;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String getByI18nTagSql = "select * from sys_language where `i18n_tag`=?";

	/**
	 * 根据i18nTag获取
	 */
	public Language getByI18nTag(String i18nTag) {
		try {
			Language language = super.getJdbcTemplate().queryForObject(getByI18nTagSql, entityRowMapper, i18nTag);
			return language;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String listAllSql = "select * from sys_language order by `order_index`";

	/**
	 * 获取所有
	 */
	public List<Language> listAll() {
		try {
			List<Language> languages = super.getJdbcTemplate().query(listAllSql, entityRowMapper);
			return languages;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String listAllEnableSql = "select * from sys_language where enable=1 order by `order_index`";

	/**
	 * 获取所有启用
	 */
	public List<Language> listAllEnable() {
		try {
			List<Language> languages = super.getJdbcTemplate().query(listAllEnableSql, entityRowMapper);
			return languages;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String insertSql = "insert into sys_language(`name`,`i18n_tag`,`order_index`,`enable`,`memo`,`create_time`,`update_time`) values"
			+ "(?,?,?,?,?,now(),now())";

	/**
	 * 创建语言
	 */
	public synchronized Language create(final Language language) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		super.getJdbcTemplate().update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {
					"id"
				});
				int count = 1;
				ps.setString(count++, language.getName());
				ps.setString(count++, language.getI18nTag());
				ps.setInt(count++, (language.getOrderIndex() == null) ? 0 : language.getOrderIndex());
				ps.setInt(count++, (language.getEnable() == null) ? 0 : language.getEnable());
				ps.setString(count++, language.getMemo());
				return ps;
			}
		}, keyHolder);

		language.setId(keyHolder.getKey().longValue());
		return language;
	}

	private static final String updateSql = "update sys_language set `name`=?,`i18n_tag`=?,`order_index`=?,"
			+ "`enable`=?,`memo`=?,`update_time`=now() where `id`=?";

	/**
	 * 修改语言
	 */
	public void update(Language language) {
		Object[] args = new Object[] {
				language.getName(), language.getI18nTag(), language.getOrderIndex(), language.getEnable(),
				language.getMemo(), language.getId()
		};
		super.getJdbcTemplate().update(updateSql, args);
	}

	private static final String deleteSql = "delete from sys_language where id=?";

	/**
	 * 删除语言
	 */
	public boolean delete(String id) {
		super.getJdbcTemplate().update(deleteSql, id);
		return true;
	}

	/**
	 * 分页查询语言列表（模糊查询，条件为：名称、语言标识）
	 */
	public Page<Language> pagingList(String name, String i18nTag, Page<Language> page) {
		String countSql = "select count(id) from sys_language where 1=1";
		String queryByPageSql = "select * from sys_language where 1=1";

		if (StringUtils.hasText(name)) {
			countSql += " and `name` like :likeName";
			queryByPageSql += " and `name` like :likeName";
		}

		if (StringUtils.hasText(i18nTag)) {
			countSql += " and `i18n_tag` like :likeI18nTag";
			queryByPageSql += " and `i18n_tag` like :likeI18nTag";
		}

		queryByPageSql += " order by `order_index`";
		queryByPageSql += " limit :start,:limit";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeName", super.filterKeyPara(name));
		params.put("likeI18nTag", super.filterKeyPara(i18nTag));
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());

		page.setTotal(super.getTotalCount(countSql, params));
		page.setRows(super.getNamedParameterJdbcTemplate().query(queryByPageSql, params, entityRowMapper));
		return page;
	}

	/**
	 * 分页查询用户列表（关键字模糊查询，模糊查询内容：帐号、姓名、电子邮箱、电话）
	 */
	public Page<Language> pagingList(String searchKey, Page<Language> page) {
		String countSql = "select count(id) from sys_language where 1=1";
		String queryByPageSql = "select * from sys_language where 1=1";

		if (StringUtils.hasText(searchKey)) {
			countSql += " and ((`name` like :likeName) or (`i18n_tag` like :likeI18nTag))";
			queryByPageSql += " and ((`name` like :likeName) or (`i18n_tag` like :likeI18nTag))";
		}

		queryByPageSql += " order by `order_index`";
		queryByPageSql += " limit :start,:limit";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("likeName", super.filterKeyPara(searchKey));
		params.put("likeI18nTag", super.filterKeyPara(searchKey));
		params.put("start", page.getStart());
		params.put("limit", page.getLimit());

		page.setTotal(super.getTotalCount(countSql, params));
		page.setRows(super.getNamedParameterJdbcTemplate().query(queryByPageSql, params, entityRowMapper));
		return page;
	}
}
