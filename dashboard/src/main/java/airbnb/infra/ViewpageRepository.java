package airbnb.infra;

import airbnb.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "viewpages", path = "viewpages")
public interface ViewpageRepository
    extends PagingAndSortingRepository<Viewpage, Long> {}
