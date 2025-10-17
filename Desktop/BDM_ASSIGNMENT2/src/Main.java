import java.sql.*;

public class Main {
    static final String URL = "jdbc:postgresql://vatsal1-db.ckz8sgg0ybci.us-east-1.rds.amazonaws.com:5432/vatsal1-db";
    static final String USER = "vatsal";
    static final String PASSWORD = "16144067#Reso";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected!");

            dropTables(conn);
            createTables(conn);
            insertCompanies(conn);
            insertStockPrices(conn);
            deleteStockPrices(conn);
            queryOne(conn);
            // Add other queries as needed
            querytwo.queryTwo(conn);
            querythree.queryThree(conn);

            conn.close();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void dropTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS stockprice");
        stmt.executeUpdate("DROP TABLE IF EXISTS company CASCADE");
        System.out.println("Tables dropped.");
        stmt.close();
    }

    static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE company (" +
                        "id INTEGER PRIMARY KEY," +
                        "name VARCHAR(50)," +
                        "ticker CHAR(10)," +
                        "annualRevenue DECIMAL(14,2)," +
                        "numEmployees INTEGER)"
        );
        stmt.executeUpdate(
                "CREATE TABLE stockprice (" +
                        "companyId INTEGER," +
                        "priceDate DATE," +
                        "openPrice DECIMAL(10,2)," +
                        "highPrice DECIMAL(10,2)," +
                        "lowPrice DECIMAL(10,2)," +
                        "closePrice DECIMAL(10,2)," +
                        "volume INTEGER," +
                        "PRIMARY KEY (companyId, priceDate)," +
                        "FOREIGN KEY (companyId) REFERENCES company(id))"
        );
        System.out.println("Tables created.");
        stmt.close();
    }

    static void insertCompanies(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO company VALUES (?, ?, ?, ?, ?)");
        ps.setInt(1, 1); ps.setString(2, "Apple"); ps.setString(3, "AAPL     ");
        ps.setBigDecimal(4, new java.math.BigDecimal("387540000000.00")); ps.setInt(5, 154000); ps.executeUpdate();
        ps.setInt(1, 2); ps.setString(2, "GameStop"); ps.setString(3, "GME      ");
        ps.setBigDecimal(4, new java.math.BigDecimal("611000000.00")); ps.setInt(5, 12000); ps.executeUpdate();
        ps.setInt(1, 3); ps.setString(2, "Handy Repair"); ps.setString(3, null);
        ps.setBigDecimal(4, new java.math.BigDecimal("2000000.00")); ps.setInt(5, 50); ps.executeUpdate();
        ps.setInt(1, 4); ps.setString(2, "Microsoft"); ps.setString(3, "MSFT     ");
        ps.setBigDecimal(4, new java.math.BigDecimal("198270000000.00")); ps.setInt(5, 221000); ps.executeUpdate();
        ps.setInt(1, 5); ps.setString(2, "StartUp"); ps.setString(3, null);
        ps.setBigDecimal(4, new java.math.BigDecimal("50000.00")); ps.setInt(5, 3); ps.executeUpdate();
        System.out.println("Company records inserted.");
        ps.close();
    }

    static void insertStockPrices(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO stockprice VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        // Week of Aug 15-19 data
        // Apple (AAPL)
        ps.setInt(1, 1); ps.setDate(2, Date.valueOf("2022-08-15")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("171.52")); ps.setBigDecimal(4, new java.math.BigDecimal("173.39")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("171.35")); ps.setBigDecimal(6, new java.math.BigDecimal("173.19")); 
        ps.setInt(7, 54091700); ps.executeUpdate();

        ps.setInt(1, 1); ps.setDate(2, Date.valueOf("2022-08-16")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("172.78")); ps.setBigDecimal(4, new java.math.BigDecimal("173.71")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("171.66")); ps.setBigDecimal(6, new java.math.BigDecimal("173.03")); 
        ps.setInt(7, 56377100); ps.executeUpdate();

        // Microsoft (MSFT)
        ps.setInt(1, 4); ps.setDate(2, Date.valueOf("2022-08-15")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("288.32")); ps.setBigDecimal(4, new java.math.BigDecimal("291.99")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("287.75")); ps.setBigDecimal(6, new java.math.BigDecimal("291.99")); 
        ps.setInt(7, 18222100); ps.executeUpdate();

        ps.setInt(1, 4); ps.setDate(2, Date.valueOf("2022-08-16")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("289.74")); ps.setBigDecimal(4, new java.math.BigDecimal("294.04")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("289.72")); ps.setBigDecimal(6, new java.math.BigDecimal("292.71")); 
        ps.setInt(7, 18318800); ps.executeUpdate();

        // Aug 22-23 data (for query 2)
        ps.setInt(1, 1); ps.setDate(2, Date.valueOf("2022-08-22")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("167.23")); ps.setBigDecimal(4, new java.math.BigDecimal("169.86")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("167.10")); ps.setBigDecimal(6, new java.math.BigDecimal("169.86")); 
        ps.setInt(7, 60794500); ps.executeUpdate();

        ps.setInt(1, 1); ps.setDate(2, Date.valueOf("2022-08-23")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("168.78")); ps.setBigDecimal(4, new java.math.BigDecimal("168.71")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("166.65")); ps.setBigDecimal(6, new java.math.BigDecimal("167.23")); 
        ps.setInt(7, 58191200); ps.executeUpdate();

        ps.setInt(1, 4); ps.setDate(2, Date.valueOf("2022-08-22")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("277.22")); ps.setBigDecimal(4, new java.math.BigDecimal("280.42")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("276.34")); ps.setBigDecimal(6, new java.math.BigDecimal("277.75")); 
        ps.setInt(7, 18934200); ps.executeUpdate();

        ps.setInt(1, 4); ps.setDate(2, Date.valueOf("2022-08-23")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("276.44")); ps.setBigDecimal(4, new java.math.BigDecimal("278.85")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("275.82")); ps.setBigDecimal(6, new java.math.BigDecimal("276.44")); 
        ps.setInt(7, 17552400); ps.executeUpdate();

        // Aug 30 data (for query 3)
        ps.setInt(1, 1); ps.setDate(2, Date.valueOf("2022-08-30")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("169.69")); ps.setBigDecimal(4, new java.math.BigDecimal("170.99")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("168.78")); ps.setBigDecimal(6, new java.math.BigDecimal("170.03")); 
        ps.setInt(7, 51218200); ps.executeUpdate();

        ps.setInt(1, 4); ps.setDate(2, Date.valueOf("2022-08-30")); 
        ps.setBigDecimal(3, new java.math.BigDecimal("262.97")); ps.setBigDecimal(4, new java.math.BigDecimal("264.57")); 
        ps.setBigDecimal(5, new java.math.BigDecimal("261.73")); ps.setBigDecimal(6, new java.math.BigDecimal("262.97")); 
        ps.setInt(7, 17996200); ps.executeUpdate();

        System.out.println("StockPrice records inserted.");
        ps.close();
    }

    static void deleteStockPrices(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM stockprice WHERE companyId = 2";  // Only delete GameStop records
        int n = stmt.executeUpdate(sql);
        System.out.println("Deleted " + n + " stockprice records.");
        stmt.close();
    }

    static void queryOne(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT name, annualRevenue, numEmployees FROM company " +
                "WHERE numEmployees > 10000 OR annualRevenue < 1000000 " +
                "ORDER BY name ASC";
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.println("\nQuery One Results:");
        System.out.println("Column Information:");
        System.out.println(resultSetMetaDataToString(rs.getMetaData()));
        System.out.println("Data:");
        System.out.println(resultSetToString(rs, 10));
        
        rs.close();
        stmt.close();
    }

    static String resultSetToString(ResultSet rs, int maxrows) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        
        // Get max width for each column based on column name and data
        int[] maxWidths = new int[cols];
        for (int i = 1; i <= cols; i++) {
            maxWidths[i-1] = meta.getColumnName(i).length();
        }
        
        // Store results in memory to calculate max widths
        Object[][] data = new Object[maxrows][cols];
        int rowCount = 0;
        while (rs.next() && rowCount < maxrows) {
            for (int i = 1; i <= cols; i++) {
                Object value = rs.getObject(i);
                // Format the value based on its type
                String formattedValue;
                if (value == null) {
                    formattedValue = "N/A";
                } else if (meta.getColumnType(i) == java.sql.Types.NUMERIC || 
                         meta.getColumnType(i) == java.sql.Types.DECIMAL ||
                         meta.getColumnType(i) == java.sql.Types.DOUBLE) {
                    if (meta.getColumnName(i).toLowerCase().contains("price") ||
                        meta.getColumnName(i).toLowerCase().contains("revenue")) {
                        formattedValue = String.format("$%,.2f", rs.getDouble(i));
                    } else {
                        formattedValue = String.format("%,d", rs.getLong(i));
                    }
                } else if (meta.getColumnType(i) == java.sql.Types.CHAR ||
                          meta.getColumnType(i) == java.sql.Types.VARCHAR) {
                    formattedValue = rs.getString(i) != null ? rs.getString(i).trim() : "N/A";
                } else {
                    formattedValue = value.toString();
                }
                data[rowCount][i-1] = formattedValue;
                maxWidths[i-1] = Math.max(maxWidths[i-1], formattedValue.length());
            }
            rowCount++;
        }
        
        // Print header with proper spacing
        for (int i = 1; i <= cols; i++) {
            String format = "%-" + (maxWidths[i-1] + 2) + "s";
            sb.append(String.format(format, meta.getColumnName(i).toUpperCase()));
        }
        sb.append("\n");
        
        // Print separator line
        for (int i = 1; i <= cols; i++) {
            for (int j = 0; j < maxWidths[i-1]; j++) sb.append("-");
            sb.append("  ");
        }
        sb.append("\n");
        
        // Print data
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < cols; col++) {
                String format = "%-" + (maxWidths[col] + 2) + "s";
                sb.append(String.format(format, data[row][col]));
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    static String resultSetMetaDataToString(ResultSetMetaData meta) throws SQLException {
        StringBuilder sb = new StringBuilder();
        int cols = meta.getColumnCount();
        for (int i = 1; i <= cols; i++) {
            sb.append(String.format("Column %d: Name=%s, Type=%s\n", i, meta.getColumnName(i), meta.getColumnTypeName(i)));
        }
        return sb.toString();
    }
}
