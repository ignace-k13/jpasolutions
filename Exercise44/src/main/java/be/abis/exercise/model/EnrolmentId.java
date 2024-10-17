package be.abis.exercise.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrolmentId implements Serializable {

    private Session session;
    private int enrolmentInSession;

    public int getEnrolmentInSession() {
        return enrolmentInSession;
    }

    public void setEnrolmentInSession(int enrolmentInSession) {
        this.enrolmentInSession = enrolmentInSession;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return enrolmentInSession == that.enrolmentInSession && session.getSessionId()==that.session.getSessionId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(session.getSessionId(), enrolmentInSession);
    }
}
