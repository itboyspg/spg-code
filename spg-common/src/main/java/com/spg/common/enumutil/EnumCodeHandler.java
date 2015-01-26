/**
 * 
 */
package com.spg.common.enumutil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 项目名称：
 * 
 * @description: 自定义mybatis枚举转换handler，对象属性是枚举类型，入库后是code，查出来后是枚举
 * 
 * @author spg
 * 
 * @create_time：2014年5月28日 下午6:49:04
 * 
 * @version V1.0.0
 * 
 */
public class EnumCodeHandler extends BaseTypeHandler<EnumInterface>
{

	private static final Log LOGGER = LogFactory.getLog(EnumCodeHandler.class);

	private Class<EnumInterface> type;

	private final EnumInterface[] enums;

	/**
	 * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
	 * 
	 * @param type
	 *            配置文件中设置的转换类
	 */
	public EnumCodeHandler(Class<EnumInterface> type)
	{
		LOGGER.debug(String.format("enter function:%s", type));
		if (type == null)
		{
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null)
		{
			throw new IllegalArgumentException(type.getSimpleName()
					+ " does not represent an enum type.");
		}
	}

	@Override
	public EnumInterface getNullableResult(ResultSet rs, String columnName)
			throws SQLException
	{
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		int i = rs.getInt(columnName);

		if (rs.wasNull())
		{
			return null;
		} else
		{
			// 根据数据库中的code值，定位EnumInterface子类
			return locateEnumInterface(i);
		}
	}

	@Override
	public EnumInterface getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException
	{
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		int i = rs.getInt(columnIndex);
		if (rs.wasNull())
		{
			return null;
		} else
		{
			// 根据数据库中的code值，定位EnumInterface子类
			return locateEnumInterface(i);
		}
	}

	@Override
	public EnumInterface getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException
	{
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		int i = cs.getInt(columnIndex);
		if (cs.wasNull())
		{
			return null;
		} else
		{
			// 根据数据库中的code值，定位EnumInterface子类
			return locateEnumInterface(i);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			EnumInterface parameter, JdbcType jdbcType) throws SQLException
	{
		// baseTypeHandler已经帮我们做了parameter的null判断
		ps.setInt(i, parameter.getCode());

	}

	/**
	 * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
	 * 
	 * @param code
	 *            数据库中存储的自定义code属性
	 * @return code对应的枚举类
	 */
	private EnumInterface locateEnumInterface(int code)
	{
		for (EnumInterface status : enums)
		{
			if (status.getCode() == code)
			{
				return status;
			}
		}
		throw new IllegalArgumentException("unable enum type with code：" + code + ", please check"
				+ type.getSimpleName());
	}
}
