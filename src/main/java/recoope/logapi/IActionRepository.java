package recoope.logapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActionRepository extends MongoRepository<Action, String> {
    Action findFirstByAction(String actionName);
}
