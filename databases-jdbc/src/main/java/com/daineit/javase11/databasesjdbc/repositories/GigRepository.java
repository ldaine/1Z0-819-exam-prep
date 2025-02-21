package com.daineit.javase11.databasesjdbc.repositories;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Gig;
import com.daineit.javase11.databasesjdbc.models.GigReport;

public class GigRepository extends Repository<Gig> {

    public GigRepository() {
        super("gigs");
    }

    public List<GigReport> getGigReport() throws SQLException {
        List<GigReport> result = new ArrayList<>();
        final LocalDate startDate = LocalDate.of(2022, 10, 1);
        final LocalDate endDate = LocalDate.now();
        String sql = "{call gig_report(?,?)}";
        try (Connection conn = super.getDbConnection(); CallableStatement statement = conn.prepareCall(sql)) {
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2, Date.valueOf(endDate));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("date");
                String act = rs.getString("act");
                String recordlabel = rs.getString("recordlabel");
                String venue = rs.getString("venue");
                int ticketsSold = rs.getInt("ticketsSold");
                int capacity = rs.getInt("capacity");

                result.add(new GigReport(date, act, recordlabel, venue, ticketsSold, capacity));
            }
        }
        return result;
    }

    public BigDecimal getTotalSales() throws SQLException {
        BigDecimal result = null;
        String sql = "{? = call get_total_sales()}";
        try (Connection conn = super.getDbConnection(); CallableStatement statement = conn.prepareCall(sql)) {
            statement.registerOutParameter(1, Types.DECIMAL);
            statement.execute();

            result = statement.getBigDecimal(1);
            // System.out.println(result.getClass());
            // System.out.println(result);
        }
        return result;
    }

    @Override
    public List<Gig> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void add(Gig item) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void update(Gig item) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
