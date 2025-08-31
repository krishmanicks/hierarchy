package com.zoho.hierarchy.repository;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.DeleteQuery;
import com.adventnet.ds.query.DeleteQueryImpl;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.ds.query.SelectQuery;
import com.adventnet.ds.query.SelectQueryImpl;
import com.adventnet.ds.query.Table;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.zoho.hierarchy.hr.HrApprovalRequest;
import com.zoho.hierarchy.internal.Tables;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class HrRepositoryImpl implements HrRepository {

    @Override
    public <S extends HrApprovalRequest> S save(S entity) throws Exception {
        DataObject dataObject = new WritableDataObject();
        Row row = prepareRow(entity);
        try {
            dataObject.addRow(row);
            DataAccess.add(dataObject);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        long generatedId = (long) row.get(Tables.HrApprovalTable.ID);
        entity.setId(generatedId);
        return entity;
    }

    private Row prepareRow(HrApprovalRequest request) {
        Row row = new Row(Tables.HrApprovalTable.TABLE_NAME);
        row.set(Tables.HrApprovalTable.ID, request.getId());
        row.set(Tables.HrApprovalTable.USER_ID, request.getUserId());

        return row;
    }

    @Override
    public Collection<HrApprovalRequest> findAllById(Long id) throws Exception {
        return null;
    }

    public Collection<HrApprovalRequest> findAll() {
        SelectQuery selectQuery = prepareSelectQuery();
        Iterator<Row> rows;

        try {
            DataObject dataObject = DataAccess.get(selectQuery);
            rows = dataObject.getRows(Tables.HrApprovalTable.TABLE_NAME);

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        List<HrApprovalRequest> hrApprovalRequests = new ArrayList<>();
        while (rows.hasNext()) {
            hrApprovalRequests.add(buildFromRow(rows.next()));
        }

        return hrApprovalRequests;
    }

    @Override
    public void deleteByUserId(long userId) {
        DeleteQuery query = new DeleteQueryImpl(Tables.HrApprovalTable.TABLE_NAME);
        query.setCriteria(new Criteria(Column.getColumn(Tables.HrApprovalTable.TABLE_NAME, Tables.HrApprovalTable.USER_ID), userId, QueryConstants.EQUAL));
        try {
            DataAccess.delete(query);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<HrApprovalRequest> findByCriteria(@NotNull Criteria criteria) throws Exception {
        SelectQuery selectQuery = prepareSelectQuery();
        selectQuery.setCriteria(criteria);
        try {
            DataObject dataObject = DataAccess.get(selectQuery);
            if (dataObject.isEmpty()) {
                return Optional.empty();
            }
            Row row = dataObject.getFirstRow(Tables.HrApprovalTable.TABLE_NAME);
            return Optional.of(buildFromRow(row));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private SelectQuery prepareSelectQuery() {
        SelectQuery selectQuery = new SelectQueryImpl(Table.getTable(Tables.HrApprovalTable.TABLE_NAME));
        selectQuery.addSelectColumn(Column.getColumn(Tables.HrApprovalTable.TABLE_NAME, Tables.HrApprovalTable.ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.HrApprovalTable.TABLE_NAME, Tables.HrApprovalTable.USER_ID));

        return selectQuery;
    }

    private HrApprovalRequest buildFromRow(Row row) {
        HrApprovalRequest approvalRequest = new HrApprovalRequest();
        approvalRequest.setId((long) row.get(Tables.HrApprovalTable.ID));
        approvalRequest.setUserId((long) row.get(Tables.HrApprovalTable.USER_ID));

        return approvalRequest;
    }

    @Override
    public Optional<HrApprovalRequest> findById(Long aLong) throws Exception {
        return Optional.empty();
    }


    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public void delete(HrApprovalRequest entity) throws Exception {

    }

    @Override
    public <S extends HrApprovalRequest> S update(S entity) throws Exception {
        return null;
    }

}
