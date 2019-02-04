package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.CategoryWarnaDao;
import id.co.mandiri.entity.CategoryWarna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryWarnaService implements ServiceCrudDataTablesPattern<CategoryWarna, String> {

    @Autowired
    private CategoryWarnaDao categoryDao;

    @Override
    public CategoryWarna findId(String s) {
        return categoryDao.findId(s);
    }

    @Override
    public List<CategoryWarna> findAll() {
        return null;
    }

    @Override
    @Transactional
    public CategoryWarna save(CategoryWarna value) {
        return categoryDao.save(value);
    }

    @Override
    @Transactional
    public CategoryWarna update(CategoryWarna value) {
        return categoryDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(CategoryWarna value) {
        return categoryDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return categoryDao.removeById(s);
    }

    @Override
    public DataTablesResponse<CategoryWarna> datatables(DataTablesRequest<CategoryWarna> params) {
        List<CategoryWarna> values = categoryDao.datatables(params);
        Long rowCount = categoryDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
