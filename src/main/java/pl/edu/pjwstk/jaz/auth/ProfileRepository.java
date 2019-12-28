package pl.edu.pjwstk.jaz.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class ProfileRepository {

    @PersistenceContext
    private EntityManager em;

    private final Map<String, User> userMap = new HashMap<>();

//    // startTx()
//    @Transactional
//    public void sampleCodeWithPC() {
//        var profile = new ProfileEntity(use);
//
//        em.persist(profile);
//
//        final ProfileEntity profileEntity = em.find(ProfileEntity.class, 7L);
//        var list = em.createQuery("from ProfileEntity where firstName = :name", ProfileEntity.class)
//                .setParameter("name", "maciej")
//                .getResultList();
//
//        var passwordEncoder = new BCryptPasswordEncoder();
//        final String rawPassword = "xGdXi7Qb5EK4";
//
//        System.out.println("hashed password try 1: " + passwordEncoder.encode(rawPassword));
//        final String hashedPassword = passwordEncoder.encode(rawPassword);
//        System.out.println("hashed password try 2: " + hashedPassword);
//
//        System.out.println("Does password match?: " + passwordEncoder.matches(rawPassword, hashedPassword));
//
//        System.out.println();
//    }
//    // commitTx()Mem

    User requireUser(String username) {
        if (!userMap.containsKey(username)) {
            throw new IllegalStateException("Required user does not exist.");
        }
        return userMap.get(username);
    }

    Optional<ProfileEntity> findUserByUsername(String username) {
        var list = em.createQuery("from ProfileEntity where username = username", ProfileEntity.class)
                .getResultList();

        if(!list.isEmpty()){
            for(ProfileEntity p : list){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Transactional
    void addUser(User user){

        var list = em.createQuery("from ProfileEntity where username = :username", ProfileEntity.class)
                .getResultList();

        if(!list.isEmpty()){
            throw new IllegalStateException(String.format("User %s already exists.", user.getUsername()));
        }

        if (userMap.containsKey(user.getUsername())) {
            throw new IllegalStateException(String.format("User %s already exists.", user.getUsername()));
        }

        var profile = new ProfileEntity(user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),user.getEmail(),user.getBirthDate());
        em.persist(profile);
        userMap.put(user.getUsername(), user);

//        final ProfileEntity profileEntity = em.find(ProfileEntity.class, user)'
//        em.persist(user);
    }
}
