package domain.database;

public class DatabaseLayer {
    public PersonsDatabase Persons = new PersonsDatabase();
    public RoleDatabase Roles = new RoleDatabase();
    public CourseDatabase Courses = new CourseDatabase();
    public CollegeStudentDatabase CollegeStudents = new CollegeStudentDatabase();
    public CompanyDatabase Companies = new CompanyDatabase();
    public CompanyEmployeeDatabase CompanyEmployees = new CompanyEmployeeDatabase();
    public CompanyTeamDatabase CompanyTeams = new CompanyTeamDatabase();
    public JobApplicationDatabase CompanyApplications = new JobApplicationDatabase();
    public JobCandidateDatabase JobCandidates = new JobCandidateDatabase();
    public PersonNotificationDatabase PersonNotifications = new PersonNotificationDatabase();
    public TrainingCertificateDatabase TrainingCertificates = new TrainingCertificateDatabase();
    public TrainingModuleDatabase TrainingModules = new TrainingModuleDatabase();
    public TrainingQuestionDatabase TrainingQuestions = new TrainingQuestionDatabase();
    public TrainingModuleDataDatabase TrainingModuleDataDatabase = new TrainingModuleDataDatabase();
    public JobPostingDatabase JobPostings = new JobPostingDatabase();
    public JobApplicationDatabase JobApplications = new JobApplicationDatabase();
    public PersonRoleDatabase PersonRoles = new PersonRoleDatabase();
    public TrainingModuleDataCompletionsDatabase TrainingModuleDataCompletionsDatabase = new TrainingModuleDataCompletionsDatabase();
}