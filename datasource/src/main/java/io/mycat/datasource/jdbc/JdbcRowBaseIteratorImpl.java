package io.mycat.datasource.jdbc;

import io.mycat.MycatException;
import io.mycat.api.collector.RowBaseIterator;
import io.mycat.beans.mycat.MycatRowMetaData;
import io.mycat.logTip.MycatLogger;
import io.mycat.logTip.MycatLoggerFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcRowBaseIteratorImpl implements RowBaseIterator {

  final static MycatLogger LOGGER = MycatLoggerFactory.getLogger(JdbcRowBaseIteratorImpl.class);

  private final ClearableSession session;
  private final Statement statement;
  private final ResultSet resultSet;

  public JdbcRowBaseIteratorImpl(ClearableSession session,
      Statement statement, ResultSet resultSet) {
    this.session = session;
    this.statement = statement;
    this.resultSet = resultSet;
  }


  private String toMessage(Exception e) {
    return e.toString();
  }

  @Override
  public MycatRowMetaData metaData() {
    try {
      return new JdbcRowMetaDataImpl(resultSet.getMetaData());
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public boolean next() {
    try {
      return resultSet.next();
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public void close() {
    try {
      resultSet.close();
    } catch (Exception e) {
      LOGGER.error("", e);
    }
    try {
      statement.close();
    } catch (Exception e) {
      LOGGER.error("", e);
    }
    this.session.clear();
  }

  @Override
  public boolean wasNull() {
    try {
      return resultSet.wasNull();
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public String getString(int columnIndex) {
    try {
      return resultSet.getString(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public boolean getBoolean(int columnIndex) {
    try {
      return resultSet.getBoolean(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public byte getByte(int columnIndex) {
    try {
      return resultSet.getByte(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public short getShort(int columnIndex) {
    try {
      return resultSet.getShort(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public int getInt(int columnIndex) {
    try {
      return resultSet.getInt(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public long getLong(int columnIndex) {
    try {
      return resultSet.getLong(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public float getFloat(int columnIndex) {
    try {
      return resultSet.getFloat(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public double getDouble(int columnIndex) {
    try {
      return resultSet.getDouble(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public byte[] getBytes(int columnIndex) {
    try {
      return resultSet.getBytes(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public Date getDate(int columnIndex) {
    try {
      return resultSet.getDate(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public Time getTime(int columnIndex) {
    try {
      return resultSet.getTime(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public Timestamp getTimestamp(int columnIndex) {
    try {
      return resultSet.getTimestamp(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public InputStream getAsciiStream(int columnIndex) {
    try {
      return resultSet.getAsciiStream(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public InputStream getBinaryStream(int columnIndex) {
    try {
      return resultSet.getBinaryStream(columnIndex);
    } catch (Exception e) {
      throw new MycatException(toMessage(e));
    }
  }

  @Override
  public Object getObject(int columnIndex) {
    try {
      return resultSet.getObject(columnIndex);
    } catch (Exception e) {
      throw new MycatException(e);
    }
  }

  @Override
  public BigDecimal getBigDecimal(int columnIndex) {
    try {
      return resultSet.getBigDecimal(columnIndex);
    } catch (Exception e) {
      throw new MycatException(e);
    }
  }

  public List<Map<String, Object>> getResultSetMap() {
    return getResultSetMap(this);
  }

  private List<Map<String, Object>> getResultSetMap(JdbcRowBaseIteratorImpl iterator) {
    MycatRowMetaData metaData = iterator.metaData();
    int columnCount = metaData.getColumnCount();
    List<Map<String, Object>> resultList = new ArrayList<>();
    while (iterator.next()) {
      HashMap<String, Object> row = new HashMap<>(columnCount);
      for (int i = 1; i <= columnCount; i++) {
        row.put(metaData.getColumnName(i), iterator.getObject(i));
      }
      resultList.add(row);
    }
    return resultList;
  }

}