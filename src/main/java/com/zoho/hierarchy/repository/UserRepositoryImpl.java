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
import com.zoho.hierarchy.dto.User;
import com.zoho.hierarchy.internal.Tables;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public <S extends User> S save(S entity) {
        DataObject dataObject = new WritableDataObject();
        Row row = prepareRow(entity);
        try {
            dataObject.addRow(row);
            DataAccess.add(dataObject);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        long generatedId = (long) row.get(Tables.UserTable.ID);
        entity.setId(generatedId);
        return entity;
    }

    @Override
    public Collection<User> findAllById(Long id) {
        return null;
    }

    private Row prepareRow(User user) {
        Row row = new Row(Tables.UserTable.TABLE_NAME);
        row.set(Tables.UserTable.NAME, user.getName());
        row.set(Tables.UserTable.AGE, user.getAge());
        row.set(Tables.UserTable.EMAIL, user.getEmail());
        row.set(Tables.UserTable.ROLE, user.getRole());
        row.set(Tables.UserTable.HR_APPROVAL, user.isHrApproval());
        row.set(Tables.UserTable.PHONE_NUMBER, user.getPhoneNumber());
        row.set(Tables.UserTable.TEAM, user.getTeam());
        row.set(Tables.UserTable.REPORTING_TO, user.getReportingTo());
        row.set(Tables.UserTable.APPROVAL_NEEDED, user.isApprovalNeeded());
        row.set(Tables.UserTable.UPDATED_JSON, user.getUpdatedJson());

        return row;
    }

    @Override
    public Optional<User> findByName(String name) throws Exception {
        Column column = Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.NAME);
        Criteria criteria = new Criteria(column, name, QueryConstants.EQUAL);
        return findByCriteria(criteria);
    }

    private Optional<User> findByCriteria(@NotNull Criteria criteria) throws Exception {
        SelectQuery selectQuery = prepareSelectQuery();
        selectQuery.setCriteria(criteria);
        try {
            DataObject dataObject = DataAccess.get(selectQuery);
            if (dataObject.isEmpty()) {
                return Optional.empty();
            }
            Row row = dataObject.getFirstRow(Tables.UserTable.NAME);
            return Optional.of(buildFromRow(row));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private SelectQuery prepareSelectQuery() {
        SelectQuery selectQuery = new SelectQueryImpl(Table.getTable(Tables.UserTable.NAME));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.ROLE));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.ID));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.NAME));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.EMAIL));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.AGE));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.TEAM));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.REPORTING_TO));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.PHONE_NUMBER));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.HR_APPROVAL));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.APPROVAL_NEEDED));
        selectQuery.addSelectColumn(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.UPDATED_JSON));

        return selectQuery;
    }

    private User buildFromRow(Row row) {
        User user = new User();
        user.setId((long) row.get(Tables.UserTable.ID));
        user.setName((String) row.get(Tables.UserTable.NAME));
        user.setRole((String) row.get(Tables.UserTable.ROLE));
        user.setEmail((String) row.get(Tables.UserTable.EMAIL));
        user.setReportingTo((long) row.get(Tables.UserTable.REPORTING_TO));
        user.setPhoneNumber((long) row.get(Tables.UserTable.PHONE_NUMBER));
        user.setAge((int) row.get(Tables.UserTable.AGE));
        user.setHrApproval((boolean) row.get(Tables.UserTable.HR_APPROVAL));
        user.setApprovalNeeded((boolean) row.get(Tables.UserTable.APPROVAL_NEEDED));
        user.setTeam((String) row.get(Tables.UserTable.TEAM));
        user.setTeam((String) row.get(Tables.UserTable.UPDATED_JSON));

        return user;
    }

    @Override
    public Optional<User> findById(Long id) throws Exception {
        return findByCriteria(new Criteria(
                Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.ID),
                id,
                QueryConstants.EQUAL
        ));
    }

    @Override
    public Iterable<User> findAll() throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        DeleteQuery query = new DeleteQueryImpl(Tables.UserTable.TABLE_NAME);
        query.setCriteria(new Criteria(Column.getColumn(Tables.UserTable.TABLE_NAME, Tables.UserTable.ID), id, QueryConstants.EQUAL));
        try {
            DataAccess.delete(query);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) throws Exception {
    }

    @Override
    public <S extends User> S update(S entity) throws Exception {
        UpdateQuery query = new UpdateQueryImpl(Tables.UserTable.TABLE_NAME);

        query.setUpdateColumn(Tables.UserTable.ID, entity.getId());
        query.setUpdateColumn(Tables.UserTable.NAME, entity.getName());
        query.setUpdateColumn(Tables.UserTable.EMAIL, entity.getEmail());
        query.setUpdateColumn(Tables.UserTable.AGE, entity.getAge());
        query.setUpdateColumn(Tables.UserTable.TEAM, entity.getTeam());
        query.setUpdateColumn(Tables.UserTable.ROLE, entity.getRole());
        query.setUpdateColumn(Tables.UserTable.PHONE_NUMBER, entity.getPhoneNumber());
        query.setUpdateColumn(Tables.UserTable.APPROVAL_NEEDED, entity.isApprovalNeeded());
        query.setUpdateColumn(Tables.UserTable.HR_APPROVAL, entity.isHrApproval());
        query.setUpdateColumn(Tables.UserTable.REPORTING_TO, entity.getReportingTo());
        query.setUpdateColumn(Tables.UserTable.UPDATED_JSON, entity.getUpdatedJson());

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
