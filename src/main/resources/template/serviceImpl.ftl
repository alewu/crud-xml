package ${packageName}.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.common.bean.page.PageBean;
import ${packageName}.common.bean.page.PageParams;
import ${packageName}.entity.${entityName};
import ${packageName}.dao.${entityName}DAO;
import ${packageName}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ${author}
 * @date ${date}
 * @description ${entityName ? uncap_first}Service 接口业务逻辑实现类
 */
@Service("${entityName ? uncap_first}Service")
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}> implements ${entityName}Service{
    @Autowired
    private ${entityName}DAO ${entityName ? uncap_first}DAO;

    @Override
    public PageBean<${entityName}> list${entityName}(PageParams pageParams) {
        Integer offset = pageParams.getOffset() == null ? 0 : pageParams.getOffset();
        Integer limit = pageParams.getLimit() == null ? 20 : pageParams.getLimit();
        PageHelper.startPage(offset, limit);
        List<${entityName}> list = ${entityName ? uncap_first}DAO.list${entityName}();
        PageInfo<${entityName}> pageInfo = new PageInfo<>(list);
        PageBean<${entityName}> pageBean = new PageBean<>();
        pageBean.setRecordCount(pageInfo.getTotal());
        pageBean.setPageCount(pageInfo.getPages());
        pageBean.setList(list);
        return pageBean;
    }

}