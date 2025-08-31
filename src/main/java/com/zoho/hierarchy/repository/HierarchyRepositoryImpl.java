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
import com.zoho.hierarchy.dto.Hierarchy;
import com.zoho.hierarchy.internal.Tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class HierarchyRepositoryImpl implements HierarchyRepository {

    @Override
    public <S extends Hierarchy> S save(S entity) throws Exception {
        DataObject dataObject = new WritableDataObject();
        Row row = prepareRow(entity);
        try {
            dataObject.addRow(row);
            DataAccess.add(dataObject);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        long generatedId = (long) row.get(Tables.HierarchyTable.ID);
        entity.setId(generatedId);
        return entity;
    }

    private Row prepareRow(Hierarchy entity) {
        Row row = new Row(Tables.HierarchyTable.TABLE_NAME);
        row.set(Tables.HierarchyTable.ID, entity.getId());
        row.set(Tables.HierarchyTable.REPORTING_ID, entity.getReportingTo());
        row.set(Tables.HierarchyTable.USER_ID, entity.getUserId());

        return row;
    }

    @Override
    public Optional<Hierarchy> nextHierarchy(long reportingToId) throws Exception {
        return findByUserId(reportingToId);
    }

    @Override
    public Collection<Hierarchy> findAllById(Long id) {
        Collection<Hierarchy> hierarchyArrayList = new ArrayList<>();
        SelectQuery query = prepareSelectQuery();
        query.setCriteria(new Criteria(
                Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.REPORTING_ID),
                id,
                QueryConstants.EQUAL
        ));

        try {
            DataObject dataObject = DataAccess.get(query);
            if (dataObject.isEmpty()) {
                return hierarchyArrayList;
            }
            Iterator<Row> iterator = dataObject.getRows(Tables.UserTable.NAME);
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Hierarchy hierarchy = buildFromRow(row);
                hierarchyArrayList.add(hierarchy);
            }
            return hierarchyArrayList;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Hierarchy buildFromRow(Row row) {
        Hierarchy hierarchy = new Hierarchy();
        hierarchy.setId((long) row.get(Tables.HierarchyTable.ID));
        hierarchy.setUserId((long) row.get(Tables.HierarchyTable.USER_ID));
        hierarchy.setReportingTo((long) row.get(Tables.HierarchyTable.REPORTING_ID));

        return hierarchy;
    }

    private SelectQuery prepareSelectQuery() {
        SelectQuery selectQuery = new SelectQueryImpl(Table.getTable(Tables.HierarchyTable.TABLE_NAME));
        selectQuery.addSelectColumn(Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.USER_ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.REPORTING_ID));

        return selectQuery;
    }

    @Override
    public Optional<Hierarchy> findById(Long id) throws Exception {
        return findByCriteria(new Criteria(
                Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.ID),
                id,
                QueryConstants.EQUAL
        ));
    }

    public Optional<Hierarchy> findByUserId(Long id) throws Exception {
        return findByCriteria(new Criteria(
                Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.USER_ID),
                id,
                QueryConstants.EQUAL
        ));
    }

    private Optional<Hierarchy> findByCriteria(Criteria criteria) throws Exception {
        SelectQuery query = prepareSelectQuery();
        query.setCriteria(criteria);
        try {
            DataObject dataObject = DataAccess.get(query);
            if (!dataObject.isEmpty()) {
                Row row = dataObject.getRow(Tables.HierarchyTable.TABLE_NAME);
                Hierarchy entity = buildFromRow(row);
                return Optional.of(entity);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Hierarchy> findAll() throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        DeleteQuery query = new DeleteQueryImpl(Tables.HierarchyTable.TABLE_NAME);
        query.setCriteria(new Criteria(Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.ID), id, QueryConstants.EQUAL));
        try {
            DataAccess.delete(query);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Hierarchy entity) throws Exception {

    }

    @Override
    public <S extends Hierarchy> S update(S entity) throws Exception {
        UpdateQuery query = new UpdateQueryImpl(Tables.HierarchyTable.TABLE_NAME);

        query.setUpdateColumn(Tables.HierarchyTable.USER_ID, entity.getUserId());
        query.setUpdateColumn(Tables.HierarchyTable.ID, entity.getId());
        query.setUpdateColumn(Tables.HierarchyTable.REPORTING_ID, entity.getReportingTo());
        Criteria criteria = new Criteria(Column.getColumn(Tables.HierarchyTable.TABLE_NAME, Tables.HierarchyTable.ID), entity.getId(), QueryConstants.EQUAL);
        query.setCriteria(criteria);
        try {
            DataAccess.update(query);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }
}
