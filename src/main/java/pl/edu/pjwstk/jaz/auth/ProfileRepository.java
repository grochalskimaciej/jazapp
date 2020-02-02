package pl.edu.pjwstk.jaz.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfileRepository {

    @PersistenceContext
    private EntityManager em;

    Optional<ProfileEntity> findUserByUsername(String username) {
        Query user = em.createQuery("from ProfileEntity where username = :username", ProfileEntity.class);
        List<ProfileEntity> list = user.setParameter("username", username).getResultList();

        if(!list.isEmpty()){
            for(ProfileEntity p : list){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public boolean doesAnyUserExist() {
        var list = em.createQuery("FROM ProfileEntity", ProfileEntity.class).getResultList();
        return !list.isEmpty();
    }

    @Transactional
    void addUser(User user){
        var profile = new ProfileEntity(user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),user.getEmail(),user.getBirthDate(), false);

        em.persist(profile);
    }

    @Transactional
    void addAdmin(User admin){
        var profile = new ProfileEntity(admin.getFirstName(),admin.getLastName(),admin.getUsername(),admin.getPassword(),admin.getEmail(),admin.getBirthDate(), true);

        em.persist(profile);
    }
}
