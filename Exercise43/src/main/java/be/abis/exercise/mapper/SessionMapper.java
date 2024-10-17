package be.abis.exercise.mapper;

import be.abis.exercise.dto.SessionDTO;
import be.abis.exercise.model.Session;

public class SessionMapper {

    public static SessionDTO toDTO(Session session){
        SessionDTO sessionDTO= new SessionDTO();

        sessionDTO.setSessionNumber(session.getSessionId());
        sessionDTO.setStartDate(session.getStartDate());
        sessionDTO.setInstructorFirstName(session.getInstructor().getFirstName());
        sessionDTO.setInstructorLastName(session.getInstructor().getLastName().trim());

        sessionDTO.setLocationCompany(session.getLocation().getName().trim());
        sessionDTO.setLocationTown(session.getLocation().getAddress().getTown().trim());

        sessionDTO.setKind(session.getKind());

        sessionDTO.setCancelled(session.isCancelled());

        sessionDTO.setCourseTitle(session.getCourse().getLongTitle());

        return sessionDTO;

    }
}
