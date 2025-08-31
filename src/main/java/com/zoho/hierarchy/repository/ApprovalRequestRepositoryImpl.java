package com.zoho.hierarchy.repository;

import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.DeleteQuery;
import com.adventnet.ds.query.DeleteQueryImpl;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.ds.query.SelectQuery;
import com.adventnet.ds.query.SelectQueryImpl;
import com.adventnet.ds.query.Table;
import com.adventnet.ds.query.UpdateQuery;
import com.adventnet.ds.query.UpdateQueryImpl;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.zoho.hierarchy.dto.ApprovalRequest;
import com.zoho.hierarchy.internal.Tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class ApprovalRequestRepositoryImpl implements ApprovalRequestRepository {

    @Override
    public <S extends ApprovalRequest> S save(S entity) throws Exception {
        DataObject dataObject = new WritableDataObject();
        Row row = prepareRow(entity);
        try {
            dataObject.addRow(row);
            DataAccess.add(dataObject);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        long generatedId = (long) row.get(Tables.ApprovalTable.ID);
        entity.setId(generatedId);
        return entity;
    }

    @Override
    public Collection<ApprovalRequest> findAllById(Long id) {
        Collection<ApprovalRequest> approvalRequests = new ArrayList<>();
        SelectQuery query = prepareSelectQuery();
        query.setCriteria(new Criteria(
                Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.APPROVED_BY_ID),
                id,
                QueryConstants.EQUAL
        ));

        try {
            DataObject dataObject = DataAccess.get(query);
            if (dataObject.isEmpty()) {
                return approvalRequests;
            }
            Iterator<Row> iterator = dataObject.getRows(Tables.UserTable.NAME);
            while (iterator.hasNext()) {
                Row row = iterator.next();
                ApprovalRequest approvalRequest = buildFromRow(row);
                approvalRequests.add(approvalRequest);
            }
            return approvalRequests;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private Optional<ApprovalRequest> findByCriteria(Criteria criteria) throws Exception {
        SelectQuery query = prepareSelectQuery();
        query.setCriteria(criteria);
        try {
            DataObject dataObject = DataAccess.get(query);
            if (!dataObject.isEmpty()) {
                Row row = dataObject.getRow(Tables.ApprovalTable.TABLE_NAME);
                ApprovalRequest entity = buildFromRow(row);
                return Optional.of(entity);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private ApprovalRequest buildFromRow(Row row) {
        ApprovalRequest request = new ApprovalRequest();
        request.setId((long) row.get(Tables.ApprovalTable.ID));
        request.setUserId((long) row.get(Tables.ApprovalTable.USER_ID));
        request.setApprovalId((long) row.get(Tables.ApprovalTable.APPROVED_BY_ID));
        request.setStatus((String) row.get(Tables.ApprovalTable.STATUS));

        return request;
    }


    private SelectQuery prepareSelectQuery() {
        SelectQuery selectQuery = new SelectQueryImpl(Table.getTable(Tables.ApprovalTable.TABLE_NAME));
        selectQuery.addSelectColumn(Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.USER_ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.APPROVED_BY_ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.STATUS));

        return selectQuery;
    }

    private Row prepareRow(ApprovalRequest request) {
        Row row = new Row(Tables.ApprovalTable.TABLE_NAME);
        row.set(Tables.ApprovalTable.USER_ID, request.getUserId());
        row.set(Tables.ApprovalTable.APPROVED_BY_ID, request.getApprovalId());
        row.set(Tables.ApprovalTable.STATUS, request.getStatus());

        return row;
    }

    private Criteria getIdMatchesCriteria(Long id) {
        Column idColumn = new Column(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.APPROVED_BY_ID);
        return new Criteria(idColumn, id, QueryConstants.EQUAL);
    }

    @Override
    public Optional<ApprovalRequest> findById(Long id) throws Exception {
        return findByCriteria(new Criteria(
                Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.ID),
                id,
                QueryConstants.EQUAL
        ));
    }

    @Override
    public Iterable<ApprovalRequest> findAll() throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        DeleteQuery query = new DeleteQueryImpl(Tables.ApprovalTable.TABLE_NAME);
        query.setCriteria(new Criteria(Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.ID), id, QueryConstants.EQUAL));
        try {
            DataAccess.delete(query);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ApprovalRequest entity) throws Exception {

    }

    @Override
    public <S extends ApprovalRequest> S update(S entity) throws Exception {
        UpdateQuery query = new UpdateQueryImpl(Tables.ApprovalTable.TABLE_NAME);
        query.setUpdateColumn(Tables.ApprovalTable.USER_ID, entity.getUserId());
        query.setUpdateColumn(Tables.ApprovalTable.APPROVED_BY_ID, entity.getApprovalId());
        query.setUpdateColumn(Tables.ApprovalTable.STATUS, entity.getStatus());
        Criteria criteria = new Criteria(Column.getColumn(Tables.ApprovalTable.TABLE_NAME, Tables.ApprovalTable.ID), entity.getId(), QueryConstants.EQUAL);
        query.setCriteria(criteria);
        try {
            DataAccess.update(query);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
}
