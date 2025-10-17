import java.sql.*;

public class querytwo {
    public static void queryTwo(Connection conn) throws SQLException {
        String sql = "SELECT " +
                    "c.name, " +
                    "c.ticker, " +
                    "MIN(s.lowPrice) AS minLow, " +
                    "MAX(s.highPrice) AS maxHigh, " +
                    "AVG(s.closePrice) AS avgClose, " +
                    "AVG(s.volume) AS avgVolume " +
                    "FROM company c " +
                    "JOIN stockprice s ON c.id = s.companyId " +
                    "WHERE s.priceDate BETWEEN '2022-08-22' AND '2022-08-26' " +
                    "GROUP BY c.id, c.name, c.ticker " +
                    "ORDER BY avgVolume DESC";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nQuery Two Results:");
            System.out.println("Column Information:");
            System.out.println(Main.resultSetMetaDataToString(rs.getMetaData()));
            System.out.println("Data:");
            System.out.println(Main.resultSetToString(rs, 10));
        }
    }
}
