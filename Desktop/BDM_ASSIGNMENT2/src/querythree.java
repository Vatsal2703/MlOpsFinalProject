import java.sql.*;

public class querythree {
    public static void queryThree(Connection conn) throws SQLException {
        String sql = "SELECT c.name, c.ticker, s2.closePrice " +
                    "FROM company c " +
                    "LEFT JOIN stockprice s2 " +
                    "    ON c.id = s2.companyId AND s2.priceDate = '2022-08-30' " +
                    "LEFT JOIN ( " +
                    "    SELECT companyId, AVG(closePrice) AS avgClose " +
                    "    FROM stockprice " +
                    "    WHERE priceDate BETWEEN '2022-08-15' AND '2022-08-19' " +
                    "    GROUP BY companyId " +
                    ") weekAvg ON c.id = weekAvg.companyId " +
                    "WHERE c.ticker IS NULL " +
                    "    OR ( " +
                    "        s2.closePrice IS NOT NULL AND weekAvg.avgClose IS NOT NULL " +
                    "        AND s2.closePrice >= weekAvg.avgClose - 10 " +
                    "    ) " +
                    "ORDER BY c.name ASC";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\nQuery Three Results:");
            System.out.println("Column Information:");
            System.out.println(Main.resultSetMetaDataToString(rs.getMetaData()));
            System.out.println("Data:");
            System.out.println(Main.resultSetToString(rs, 10));
        }
    }
}
