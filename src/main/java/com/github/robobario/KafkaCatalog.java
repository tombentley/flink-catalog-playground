package com.github.robobario;

import org.apache.flink.table.catalog.AbstractCatalog;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.CatalogBaseTable;
import org.apache.flink.table.catalog.CatalogDatabase;
import org.apache.flink.table.catalog.CatalogFunction;
import org.apache.flink.table.catalog.CatalogPartition;
import org.apache.flink.table.catalog.CatalogPartitionSpec;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.exceptions.CatalogException;
import org.apache.flink.table.catalog.exceptions.DatabaseAlreadyExistException;
import org.apache.flink.table.catalog.exceptions.DatabaseNotEmptyException;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.apache.flink.table.catalog.exceptions.FunctionAlreadyExistException;
import org.apache.flink.table.catalog.exceptions.FunctionNotExistException;
import org.apache.flink.table.catalog.exceptions.PartitionAlreadyExistsException;
import org.apache.flink.table.catalog.exceptions.PartitionNotExistException;
import org.apache.flink.table.catalog.exceptions.PartitionSpecInvalidException;
import org.apache.flink.table.catalog.exceptions.TableAlreadyExistException;
import org.apache.flink.table.catalog.exceptions.TableNotExistException;
import org.apache.flink.table.catalog.exceptions.TableNotPartitionedException;
import org.apache.flink.table.catalog.exceptions.TablePartitionedException;
import org.apache.flink.table.catalog.stats.CatalogColumnStatistics;
import org.apache.flink.table.catalog.stats.CatalogTableStatistics;
import org.apache.flink.table.expressions.Expression;
import org.apache.flink.table.factories.CatalogFactory;
import org.apache.flink.table.factories.FactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class KafkaCatalog extends AbstractCatalog {

    private static Logger LOG = LoggerFactory.getLogger(KafkaCatalog.class);

    private Catalog delegate;

    public KafkaCatalog(CatalogFactory.Context context) {
        super(context.getName(), "default");
        delegate = FactoryUtil.discoverFactory(context.getClassLoader(), CatalogFactory.class, "generic_in_memory")
                .createCatalog(context);
    }

    @Override
    public void open() throws CatalogException {
        LOG.info("open catalog");
        delegate.open();
    }

    @Override
    public void close() throws CatalogException {
        LOG.info("close catalog");
        delegate.close();
    }

    @Override
    public List<String> listDatabases() throws CatalogException {
        LOG.info("list databases");
        return delegate.listDatabases();
    }

    @Override
    public CatalogDatabase getDatabase(String s) throws DatabaseNotExistException, CatalogException {
        LOG.info("get database {}", s);
        return delegate.getDatabase(s);
    }

    @Override
    public boolean databaseExists(String s) throws CatalogException {
        LOG.info("database exists {}", s);
        return delegate.databaseExists(s);
    }

    @Override
    public void createDatabase(String s, CatalogDatabase catalogDatabase, boolean b) throws DatabaseAlreadyExistException, CatalogException {
        LOG.info("create database {}", s);
        delegate.createDatabase(s, catalogDatabase, b);
    }

    @Override
    public void dropDatabase(String s, boolean b, boolean b1) throws DatabaseNotExistException, DatabaseNotEmptyException, CatalogException {
        LOG.info("drop database {}", s);
        delegate.dropDatabase(s, b, b1);
    }

    @Override
    public void alterDatabase(String s, CatalogDatabase catalogDatabase, boolean b) throws DatabaseNotExistException, CatalogException {
        LOG.info("alter database {}", s);
        delegate.alterDatabase(s, catalogDatabase, b);
    }

    @Override
    public List<String> listTables(String s) throws DatabaseNotExistException, CatalogException {
        LOG.info("list tables {}", s);
        return delegate.listTables(s);
    }

    @Override
    public List<String> listViews(String s) throws DatabaseNotExistException, CatalogException {
        LOG.info("list views {}", s);
        return delegate.listViews(s);
    }

    @Override
    public CatalogBaseTable getTable(ObjectPath objectPath) throws TableNotExistException, CatalogException {
        LOG.info("get table {}", objectPath);
        return delegate.getTable(objectPath);
    }

    @Override
    public boolean tableExists(ObjectPath objectPath) throws CatalogException {
        LOG.info("table exists: {}", objectPath);
        return delegate.tableExists(objectPath);
    }

    @Override
    public void dropTable(ObjectPath objectPath, boolean b) throws TableNotExistException, CatalogException {
        LOG.info("drop table: {}, {}", objectPath, b);
        delegate.dropTable(objectPath, b);
    }

    @Override
    public void renameTable(ObjectPath objectPath, String s, boolean b) throws TableNotExistException, TableAlreadyExistException, CatalogException {
        LOG.info("rename table: {}, {}, {}", objectPath, s, b);
        delegate.renameTable(objectPath, s, b);
    }

    @Override
    public void createTable(ObjectPath objectPath, CatalogBaseTable catalogBaseTable, boolean b) throws TableAlreadyExistException, DatabaseNotExistException, CatalogException {
        LOG.info("create table: {}, {}, {}", objectPath, catalogBaseTable, b);
        delegate.createTable(objectPath, catalogBaseTable, b);
    }

    @Override
    public void alterTable(ObjectPath objectPath, CatalogBaseTable catalogBaseTable, boolean b) throws TableNotExistException, CatalogException {
        LOG.info("alter table: {}, {}, {}", objectPath, catalogBaseTable, b);
        delegate.alterTable(objectPath, catalogBaseTable, b);
    }

    @Override
    public List<CatalogPartitionSpec> listPartitions(ObjectPath objectPath) throws TableNotExistException, TableNotPartitionedException, CatalogException {
        LOG.info("list partitions: {}", objectPath);
        return delegate.listPartitions(objectPath);
    }

    @Override
    public List<CatalogPartitionSpec> listPartitions(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec) throws TableNotExistException, TableNotPartitionedException, PartitionSpecInvalidException, CatalogException {
        LOG.info("list partitions: {}, {}", objectPath, catalogPartitionSpec);
        return delegate.listPartitions(objectPath, catalogPartitionSpec);
    }

    @Override
    public List<CatalogPartitionSpec> listPartitionsByFilter(ObjectPath objectPath, List<Expression> list) throws TableNotExistException, TableNotPartitionedException, CatalogException {
        LOG.info("list partitions by filter: {}, {}", objectPath, list);
        return delegate.listPartitionsByFilter(objectPath, list);
    }

    @Override
    public CatalogPartition getPartition(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec) throws PartitionNotExistException, CatalogException {
        LOG.info("get partition: {}, {}", objectPath, catalogPartitionSpec);
        return delegate.getPartition(objectPath, catalogPartitionSpec);
    }

    @Override
    public boolean partitionExists(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec) throws CatalogException {
        LOG.info("partition exists: {}, {}", objectPath, catalogPartitionSpec);
        return delegate.partitionExists(objectPath, catalogPartitionSpec);
    }

    @Override
    public void createPartition(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec, CatalogPartition catalogPartition, boolean b) throws TableNotExistException, TableNotPartitionedException, PartitionSpecInvalidException, PartitionAlreadyExistsException, CatalogException {
        LOG.info("create partition: {}, {}", objectPath, catalogPartitionSpec);
        delegate.createPartition(objectPath, catalogPartitionSpec, catalogPartition, b);
    }

    @Override
    public void dropPartition(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec, boolean b) throws PartitionNotExistException, CatalogException {
        LOG.info("drop partition: {}, {}", objectPath, catalogPartitionSpec);
        delegate.dropPartition(objectPath, catalogPartitionSpec, b);
    }

    @Override
    public void alterPartition(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec, CatalogPartition catalogPartition, boolean b) throws PartitionNotExistException, CatalogException {
        LOG.info("alter partition: {}, {}", objectPath, catalogPartitionSpec);

    }

    @Override
    public List<String> listFunctions(String s) throws DatabaseNotExistException, CatalogException {
        LOG.info("list functions: {}", s);
        return List.of();
    }

    @Override
    public CatalogFunction getFunction(ObjectPath objectPath) throws FunctionNotExistException, CatalogException {
        LOG.info("get function: {}", objectPath);
        return null;
    }

    @Override
    public boolean functionExists(ObjectPath objectPath) throws CatalogException {
        LOG.info("function exists: {}", objectPath);
        return false;
    }

    @Override
    public void createFunction(ObjectPath objectPath, CatalogFunction catalogFunction, boolean b) throws FunctionAlreadyExistException, DatabaseNotExistException, CatalogException {
        LOG.info("create function: {}", objectPath);
    }

    @Override
    public void alterFunction(ObjectPath objectPath, CatalogFunction catalogFunction, boolean b) throws FunctionNotExistException, CatalogException {
        LOG.info("alter function: {}", objectPath);
    }

    @Override
    public void dropFunction(ObjectPath objectPath, boolean b) throws FunctionNotExistException, CatalogException {
        LOG.info("drop function: {}", objectPath);
    }

    @Override
    public CatalogTableStatistics getTableStatistics(ObjectPath objectPath) throws TableNotExistException, CatalogException {
        LOG.info("get table statistics: {}", objectPath);
        return null;
    }

    @Override
    public CatalogColumnStatistics getTableColumnStatistics(ObjectPath objectPath) throws TableNotExistException, CatalogException {
        LOG.info("get table column statistics: {}", objectPath);
        return null;
    }

    @Override
    public CatalogTableStatistics getPartitionStatistics(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec) throws PartitionNotExistException, CatalogException {
        LOG.info("get partition statistics: {}", objectPath);
        return null;
    }

    @Override
    public CatalogColumnStatistics getPartitionColumnStatistics(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec) throws PartitionNotExistException, CatalogException {
        LOG.info("get partition column statistics: {}", objectPath);
        return null;
    }

    @Override
    public void alterTableStatistics(ObjectPath objectPath, CatalogTableStatistics catalogTableStatistics, boolean b) throws TableNotExistException, CatalogException {
        LOG.info("alter table statistics: {}", objectPath);
    }

    @Override
    public void alterTableColumnStatistics(ObjectPath objectPath, CatalogColumnStatistics catalogColumnStatistics, boolean b) throws TableNotExistException, CatalogException, TablePartitionedException {
        LOG.info("alter table column statistics: {}", objectPath);
    }

    @Override
    public void alterPartitionStatistics(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec, CatalogTableStatistics catalogTableStatistics, boolean b) throws PartitionNotExistException, CatalogException {
        LOG.info("alter partition statistics: {}", objectPath);
    }

    @Override
    public void alterPartitionColumnStatistics(ObjectPath objectPath, CatalogPartitionSpec catalogPartitionSpec, CatalogColumnStatistics catalogColumnStatistics, boolean b) throws PartitionNotExistException, CatalogException {
        LOG.info("alter partition column statistics: {}", objectPath);
    }
}
