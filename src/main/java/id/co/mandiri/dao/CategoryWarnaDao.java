package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.CategoryWarna;
import id.co.mandiri.repository.CategoryWarnaRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryWarnaDao implements DaoCrudDataTablesPattern<CategoryWarna, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryWarnaRepository categoryWarnaRepository;

    @Override
    public CategoryWarna findId(String s) {
        return categoryWarnaRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<CategoryWarna> findAll() {
        return null;
    }

    @Override
    public CategoryWarna save(CategoryWarna categoryWarna) {
        return categoryWarnaRepository.save(categoryWarna);
    }

    @Override
    public CategoryWarna update(CategoryWarna categoryWarna) {
        return categoryWarnaRepository.save(categoryWarna);
    }

    @Override
    public boolean remove(CategoryWarna categoryWarna) {
        categoryWarnaRepository.delete(categoryWarna);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        categoryWarnaRepository.delete(s);
        return true;
    }

    @Override
    public List<CategoryWarna> datatables(DataTablesRequest<CategoryWarna> params) {
        String baseQuery = "select id, warna, kode, description\n" +
                "from warna_category\n" +
                "where 1 = 1 ";

        CategoryWarna param = params.getValue();

        CategoryWarnaDao.CategoryWarnaQueryCompare compare = new CategoryWarnaDao.CategoryWarnaQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by name asc ");
                else
                    query.append(" order by name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by description asc ");
                else
                    query.append(" order by description desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by id asc ");
                else
                    query.append(" order by id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new CategoryWarna(
                        resultSet.getString("id"),
                        resultSet.getString("warna"),
                        resultSet.getString("kode"),
                        resultSet.getString("description")
                ));
    }

    @Override
    public Long datatables(CategoryWarna param) {
        String baseQuery = "select count(id) as rows \n" +
                "from warna_category\n" +
                "where 1 = 1 ";

        CategoryWarnaDao.CategoryWarnaQueryCompare compare = new CategoryWarnaDao.CategoryWarnaQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class CategoryWarnaQueryCompare implements QueryComparator<CategoryWarna> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        CategoryWarnaQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(CategoryWarna param) {
            if (StringUtils.isNoneBlank(param.getId())) {
                query.append(" and lower(id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getId().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getWarna())) {
                query.append(" and lower(warna) like :warna ");
                parameterSource.addValue("warna", new StringBuilder("%")
                        .append(param.getWarna().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getDescription())) {
                query.append(" and lower(description) like :description ");
                parameterSource.addValue("description", new StringBuilder("%")
                        .append(param.getDescription().toLowerCase())
                        .append("%")
                        .toString());
            }
            return query;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
