package compass.bean;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@Searchable
public class Article {
 private Long id;
 private String title;
 private String content;
 
 @SearchableId(name="id",store=Store.YES,index=Index.NOT_ANALYZED)
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@SearchableProperty(name="title",store=Store.YES,index=Index.ANALYZED)
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
@SearchableProperty(name="content",store=Store.YES,index=Index.ANALYZED)
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
 
}
