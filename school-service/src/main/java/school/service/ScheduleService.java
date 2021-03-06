package school.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import school.dto.journal.JournalSearch;
import school.dto.schedule.GroupScheduleDTO;
import school.dto.schedule.RoomDTO;
import school.dto.schedule.ScheduleDTO;
import school.dto.schedule.SchedulePerGroupDTO;
import school.dto.schedule.UserDTO;
import school.model.Group;
import school.model.Room;
import school.model.Schedule;
import school.model.Teacher;

public interface ScheduleService {

	public enum Duration {
		day(1), week(2), month(3);
		private int value;

		private Duration(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public String toString() {

			return super.toString();
		}
	}

	public List<Schedule> findByDates(Date from, Date till);

	public void addSchedule(Schedule schedule);

	public void deleteSchedule(Schedule schedule);

	public Schedule updateSchedule(Schedule schedule);

	public List<Schedule> current(long teacherId, int roomId, long groupId,
			String date, Duration duaration);

	public List<Teacher> allTeacher();

	public List<Room> allRoom();

	public List<Group> allGroup();

	public Set<String> intervalForHeader(List<Schedule> schedule, Locale loc);

	public List<ScheduleDTO> getScheduleDto(List<Schedule> schedule, Locale loc);

	public List<SchedulePerGroupDTO> sortScheduleForTable(
			List<ScheduleDTO> schDto, Set<String> dates);

	public List<GroupScheduleDTO> getGroupName(List<Group> groups, String id);

	public List<RoomDTO> getRoomName(List<Room> room, String id);

	public List<UserDTO> getTeacherName(List<Teacher> teacher, String id);

	public String[] getScheduleWithWay(String[] dayDuration, String way,
			Locale loc);

	/**
	 * @author Ihor Uksta
	 * 
	 *         This method gets all schedules from DB by role. For teacher gets
	 *         only schedules that teacher involves in. For other staff gets all
	 *         school schedules.
	 * 
	 * @param userId
	 * @param role
	 * @return
	 */
	List<Schedule> getSchedulesByRole(long userId, String string);

	/**
	 * @author Ihor Uksta
	 * 
	 *         This method gets all schedules by some chosen period, group, and
	 *         subject.
	 * 
	 * @param search
	 * @param group
	 * @return List<Schedule>
	 */
	List<Schedule> getSchedulesForStudentMarks(JournalSearch search, Group group);

	/**
	 * @author Ihor Uksta
	 * 
	 *         This method gets all schedules from DB by role and some chosen
	 *         subject. For teacher gets only schedules that include chosen
	 *         subject and teacher involves in. For other staff gets all school
	 *         schedules by chosen subject.
	 * 
	 * @param userId
	 * @param role
	 * @param subject
	 * @return
	 */
	List<Schedule> getSchedulesByRoleAndSubject(long userId, String role,
			String subject);

}
