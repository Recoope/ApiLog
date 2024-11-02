package recoope.logapi;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ILogRepository extends MongoRepository<Log, String> {
    @Aggregation(pipeline = {
            "{ $lookup: { from: 'actions', localField: 'action_Id', foreignField: '_id', as: 'action' } }",
            "{ $unwind: '$action' }",
            "{ $group: { _id: '$action.action', qtd_acessos: { $sum: 1 } } }",
            "{ $sort: { qtd_acessos: -1 } }",
            "{ $limit: 3 }",
            "{ $project: { _id: 0, pagina: '$_id', qtd_acessos: 1 } }"
    })
    List<PageAccessDTO> findTopAccessedPages();

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'actions', localField: 'action_Id', foreignField: '_id', as: 'action' } }",
            "{ $unwind: '$action' }",
            "{ $group: { _id: '$action.action', qtd_acessos: { $sum: 1 } } }",
            "{ $sort: { qtd_acessos: 1 } }",
            "{ $limit: 3 }",
            "{ $project: { _id: 0, pagina: '$_id', qtd_acessos: 1 } }"
    })
    List<PageAccessDTO> findLeastAccessedPages();
}
