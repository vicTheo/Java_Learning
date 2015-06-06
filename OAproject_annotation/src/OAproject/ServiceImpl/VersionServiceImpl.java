package OAproject.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import OAproject.Dao.VersionDao;
import OAproject.Domain.Version;
import OAproject.Service.VersionService;
@Service("versionService")
public class VersionServiceImpl implements VersionService<Version>{
    @Resource(name="versionDao")
    private VersionDao versionDao;
	public void saveEntry(Version t) {
		// TODO Auto-generated method stub
		this.versionDao.saveEntry(t);
	}

	public void updateEntry(Version t) {
		// TODO Auto-generated method stub
		this.versionDao.updateEntry(t);
	}

	public void deleteEntryById(Serializable id) {
		// TODO Auto-generated method stub
		this.versionDao.deleteEntryById(id);
	}

	public Version getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		return (Version) this.versionDao.getDEntryById(id);
	}

	public Collection<Version> getAllEntry() {
		// TODO Auto-generated method stub
		return this.versionDao.getAllEntry();
	}

	public Long getMaxVersionId(Long kid) {
		// TODO Auto-generated method stub
		return this.versionDao.getMaxVersionId(kid);
	}

}
