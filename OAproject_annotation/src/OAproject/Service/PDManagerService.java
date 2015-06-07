package OAproject.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import org.jbpm.api.ProcessDefinition;

public interface PDManagerService {
public Collection<ProcessDefinition> getVersionList();
public void deploy(File file)throws Exception;
public void delete(String key);
public InputStream showProcessImage(String did)throws Exception;
}
