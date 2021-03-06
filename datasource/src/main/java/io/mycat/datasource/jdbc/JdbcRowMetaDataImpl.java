package io.mycat.datasource.jdbc;

import io.mycat.MycatException;
import io.mycat.beans.mycat.MycatRowMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JdbcRowMetaDataImpl implements MycatRowMetaData {

  final ResultSetMetaData resultSetMetaData;

  public JdbcRowMetaDataImpl(ResultSetMetaData resultSetMetaData) {
    this.resultSetMetaData = resultSetMetaData;
  }

  private String toMessage(Exception e) {
    return e.toString();
  }

  public int getColumnCount() {
    try {
      return resultSetMetaData.getColumnCount();
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public boolean isAutoIncrement(int column) {
    try {
      return resultSetMetaData.isAutoIncrement(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public boolean isCaseSensitive(int column) {
    try {
      return resultSetMetaData.isCaseSensitive(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public int isNullable(int column) {
    try {
      return resultSetMetaData.isNullable(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public boolean isSigned(int column) {
    try {
      return resultSetMetaData.isSigned(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public int getColumnDisplaySize(int column) {
    try {
      return resultSetMetaData.getColumnDisplaySize(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public String getColumnName(int column) {
    try {
      return resultSetMetaData.getColumnName(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public String getSchemaName(int column) {
    try {
      return resultSetMetaData.getSchemaName(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public int getPrecision(int column) {
    try {
      return resultSetMetaData.getPrecision(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public int getScale(int column) {
    try {
      return resultSetMetaData.getScale(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public String getTableName(int column) {
    try {
      return resultSetMetaData.getTableName(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public int getColumnType(int column) {
    try {
      return resultSetMetaData.getColumnType(column);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public String getColumnLabel(int i) {
    try {
      return resultSetMetaData.getColumnLabel(i);
    } catch (SQLException e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public ResultSetMetaData metaData() {
    return resultSetMetaData;
  }
}