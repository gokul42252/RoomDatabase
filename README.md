# RoomDatabase

<h1>Android Room Persistence Library Database tutorial</h1>
<img src="http://thoughtnerds.com/wp-content/uploads/2018/02/606091ab-a30b-4e79-a229-e041ed244c89-300x135.jpg" alt="" width="820" height="369" class="alignnone wp-image-512" />

This Tutorial shows how can we implement Android Room Persistence Library Database tutorial in Android studio.

The Older SQLite have some of these Disadvantages For Overcoming Android introduced Android Room Database for Handling data on Android Devices.
<ul>
 	<li>In Older SQLite databases we use A lot of boilerplate code for database operations, especially for querying.</li>
 	<li>No compile-time checks (e.g. SQL queries) in Normal SQLite Databases in Android this can be replaced by Room Persistence.</li>
 	<li>Manual schema updates (maintenance, migration scripts), SQLite Database migration is too difficult and it can be overcome by Using Android Persistence Library.</li>
</ul>
<div class="jd-descr " itemprop="articleBody">

<strong>The Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.</strong>

Android<span> </span>highly recommend using Room instead of SQLite. However, if you prefer to<span> </span>use SQLite APIs<span> </span>to work with your app's databases, Android still supports direct database access using SQLite.

</div>
There are three major components in Room:
<ul>
 	<li><strong>Database</strong>:-Contains the database holder and serves as the main access point for the underlying connection to your app's persisted, relational data.</li>
 	<li><strong>Entity</strong>:-Represents a table within the database.</li>
 	<li><strong>Dao</strong>:-Contains the methods used for accessing the database.</li>
</ul>
The class that's annotated with should<code>@Database</code> satisfy the following conditions: Be an abstract class that extends.<code>RoomDatabase</code>Include the list of entities associated with the database within the annotation.Contain a constructor that has noarguments and returns the class that is annotated with.<code>@Dao</code>At runtime, you can acquire an instance of <code>Database</code> calling or<code>Room.databaseBuilder()</code><code>Room.inMemoryDatabaseBuilder()</code>.

&nbsp;
<h5 style="text-align: left;">Room Database Architecture</h5>
<img src="http://thoughtnerds.com/wp-content/uploads/2018/02/room_architecture-300x271.png" alt="" width="494" height="446" class="alignnone wp-image-508" />

1) Adding Maven repository to your <strong class="markup--strong markup--p-strong">build.gradle,</strong> Your build.gradle project file should be defined in this way:
<pre>allprojects {
    repositories {
        jcenter()
        google()
    }
}</pre>
<p name="d418" id="d418" class="graf graf--h4 graf-after--pre">2) Adding Gradle dependencies</p>
<p name="3578" id="3578" class="graf graf--p graf-after--h4">Now, you have to add the Room dependencies on your build.gradle file of your module inside of<span> </span><em class="markup--em markup--p-em">dependency</em><span> </span>group:</p>

<pre name="fad5" id="fad5" class="graf graf--pre graf-after--p">dependencies {
    compile 'android.arch.persistence.room:runtime:1.0.0'
    annotationProcessor 'android.arch.persistence.room:compiler:1.0.0'
}</pre>
For room, database Release notes <a href="https://developer.android.com/topic/libraries/architecture/release-notes.html">Here</a>

<span>3)Now create an entity called Student. It defines attributes of your table and must to declare one field as primary key. It has an annotation to auto-generate or the user can give values. Student is annotated with </span><strong class="markup--strong markup--p-strong">@Entity</strong><span> and it is the name of the table or we can manually give Table name like </span><strong><span style="font-family: Consolas, Monaco, monospace;">@Entity(tableName= "Student")</span></strong><span>. To make field primary key, Need to annotate a field with </span><strong class="markup--strong markup--p-strong">@PrimaryKey</strong><span> and property </span><strong class="markup--strong markup--p-strong">autoGenerate<span> </span></strong><span>which assign automatic IDs like below or user can assign primary key values manually.</span>
<pre><span>@PrimaryKey</span>(<span>autoGenerate </span>= <span>true</span>)
<span>private long </span><span>studentId</span><span>;</span></pre>
4) Must create a default constructor  and  add Setters and getters to access values in entity otherwise shows error

<span>The room will create a user table with defined attributes.</span>
<pre><span>package </span>com.cretlabs.roomdatabase.entities<span>;
</span><span>
</span><span>import </span>android.arch.persistence.room.<span>Entity</span><span>;
</span><span>import </span>android.arch.persistence.room.<span>PrimaryKey</span><span>;
</span><span>import </span>android.support.annotation.<span>NonNull</span><span>;
</span><span>
</span><span>/**
</span><span> * Created by Gokul on 2/18/2018.
</span><span> */
</span><span>@Entity</span>( <span>tableName </span>= <span>"Student"</span>)
<span>public class </span>Student {
    <span>@NonNull
</span><span>    @PrimaryKey</span>(<span>autoGenerate </span>= <span>true</span>)
    <span>private long </span><span>studentId</span><span>;
</span><span>    public </span><span>Student</span>() {
    }
    <span>private long </span><span>classId</span><span>;
</span><span>    private </span>String <span>studentName</span><span>;
</span><span>    private </span>String <span>studentAddress</span><span>;
</span><span>
</span><span>    public </span><span>Student</span>(<span>@NonNull </span><span>long </span>studentId<span>, long </span>classId<span>, </span>String studentName<span>, </span>String studentAddress) {
        <span>this</span>.<span>studentId </span>= studentId<span>;
</span><span>        this</span>.<span>classId </span>= classId<span>;
</span><span>        this</span>.<span>studentName </span>= studentName<span>;
</span><span>        this</span>.<span>studentAddress </span>= studentAddress<span>;
</span><span>    </span>}

    <span>@NonNull
</span><span>    </span><span>public long </span><span>getStudentId</span>() {
        <span>return </span><span>studentId</span><span>;
</span><span>    </span>}

    <span>public void </span><span>setStudentId</span>(<span>@NonNull </span><span>long </span>studentId) {
        <span>this</span>.<span>studentId </span>= studentId<span>;
</span><span>    </span>}

    <span>public long </span><span>getClassId</span>() {
        <span>return </span><span>classId</span><span>;
</span><span>    </span>}

    <span>public void </span><span>setClassId</span>(<span>long </span>classId) {
        <span>this</span>.<span>classId </span>= classId<span>;
</span><span>    </span>}

    <span>public </span>String <span>getStudentName</span>() {
        <span>return </span><span>studentName</span><span>;
</span><span>    </span>}

    <span>public void </span><span>setStudentName</span>(String studentName) {
        <span>this</span>.<span>studentName </span>= studentName<span>;
</span><span>    </span>}

    <span>public </span>String <span>getStudentAddress</span>() {
        <span>return </span><span>studentAddress</span><span>;
</span><span>    </span>}

    <span>public void </span><span>setStudentAddress</span>(String studentAddress) {
        <span>this</span>.<span>studentAddress </span>= studentAddress<span>;
</span><span>    </span>}
}</pre>
&nbsp;

4)<span> Create a @Dao interface with name StudentDao. This interface is annotated with </span><strong class="markup--strong markup--p-strong">@Dao</strong><span> annotation. The room will generate an implementation of all defined methods. There are four annotations </span><strong class="markup--strong markup--p-strong">@Query</strong><span>, </span><strong class="markup--strong markup--p-strong">@Insert</strong><span>, </span><strong class="markup--strong markup--p-strong">@Update</strong><span>, </span><strong class="markup--strong markup--p-strong">@Delete</strong><span>to perform CRUD operations. @Query annotation is used to perform normal SQLite queries on the database.</span>
<pre><span>package </span>com.cretlabs.roomdatabase.dao<span>;
</span><span>
</span><span>import </span>android.arch.persistence.room.<span>Dao</span><span>;
</span><span>import </span>android.arch.persistence.room.<span>Delete</span><span>;
</span><span>import </span>android.arch.persistence.room.<span>Insert</span><span>;
</span><span>import </span>android.arch.persistence.room.<span>Query</span><span>;
</span><span>
</span><span>import </span>com.cretlabs.roomdatabase.entities.Student<span>;
</span><span>
</span><span>import </span>java.util.List<span>;
</span><span>
</span><span>import static </span>android.arch.persistence.room.<span>OnConflictStrategy</span>.<span>IGNORE</span><span>;
</span><span>
</span><span>/**
</span><span> * Created by Gokul on 2/18/2018.
</span><span> */
</span><span>@Dao
</span><span>public interface </span>StudentDao {

    <span>@Query</span>(<span>"SELECT * FROM Student"</span>)
    List&lt;Student&gt; <span>getAllStudents</span>()<span>;
</span><span>
</span><span>    </span><span>@Query</span>(<span>"SELECT * FROM Student WHERE  studentId= :studentId"</span>)
    Student <span>loadStudentById</span>(<span>long </span>studentId)<span>;
</span><span>
</span><span>    </span><span>@Query</span>(<span>"SELECT * FROM Student where studentName = :studentName "</span>)
    List&lt;Student&gt; <span>findStudentByName</span>(String studentName)<span>;
</span><span>
</span><span>    </span><span>@Insert</span>(<span>onConflict </span>= <span>IGNORE</span>)
    <span>void </span><span>insertStudent</span>(Student student)<span>;
</span><span>
</span><span>    </span><span>@Insert</span>(<span>onConflict </span>= <span>IGNORE</span>)
    <span>void </span><span>insertMultipleRecord</span>(List&lt;Student&gt; students)<span>;
</span><span>
</span><span>    </span><span>@Delete
</span><span>    </span><span>void </span><span>deleteStudent</span>(Student student)<span>;
</span>}</pre>
5) Create a RoomDatabase class to implement functionalities to create Room Database in Android.Need to add created entities to this class with annotation <strong>@Database</strong>, Give version to more further schema updation  in future
<pre><span>@Database</span>(<span>entities </span>= {Student.<span>class</span>}<span>, </span><span>version </span>= <span>1</span>)</pre>
Showing the full code in RoomDatabase class.
<pre><span>package </span>com.cretlabs.roomdatabase.database<span>;
</span><span>
</span><span>import </span>android.arch.persistence.room.<span>Database</span><span>;
</span><span>import </span>android.arch.persistence.room.Room<span>;
</span><span>import </span>android.content.Context<span>;
</span><span>
</span><span>import </span>com.cretlabs.roomdatabase.dao.ClassStudentDao<span>;
</span><span>import </span>com.cretlabs.roomdatabase.dao.SchoolDao<span>;
</span><span>import </span>com.cretlabs.roomdatabase.dao.StudentDao<span>;
</span><span>import </span>com.cretlabs.roomdatabase.entities.ClassStudent<span>;
</span><span>import </span>com.cretlabs.roomdatabase.entities.School<span>;
</span><span>import </span>com.cretlabs.roomdatabase.entities.Student<span>;
</span><span>
</span><span>@Database</span>(<span>entities </span>= {Student.<span>class</span>}<span>, </span><span>version </span>= <span>1</span>)
<span>public abstract class </span>RoomDatabase <span>extends </span>android.arch.persistence.room.RoomDatabase {

    <span>private static </span>RoomDatabase <span>INSTANCE</span><span>;
</span><span>
</span><span>    public abstract </span>StudentDao <span>studentDao</span>()<span>;
</span><span>
</span><span>    public static </span>RoomDatabase <span>getDatabase</span>(Context context) {
        <span>if </span>(<span>INSTANCE </span>== <span>null</span>) {
            <span>INSTANCE </span>=
                    Room.<span>databaseBuilder</span>(context<span>, </span>RoomDatabase.<span>class,</span><span>"School_DB"</span>)
                            .allowMainThreadQueries()
                            .build()<span>;
</span><span>        </span>}
        <span>return </span><span>INSTANCE</span><span>;
</span><span>    </span>}

    <span>public static void </span><span>destroyInstance</span>() {
        <span>INSTANCE </span>= <span>null;
</span><span>    </span>}
}</pre>
&nbsp;

6) Create a DataInitializer class to insert sample datas to Room database

&nbsp;
<pre><span>package </span>com.cretlabs.roomdatabase.database<span>;
</span><span>
</span><span>import </span>android.os.AsyncTask<span>;
</span><span>
</span><span>import </span>com.cretlabs.roomdatabase.entities.ClassStudent<span>;
</span><span>import </span>com.cretlabs.roomdatabase.entities.School<span>;
</span><span>import </span>com.cretlabs.roomdatabase.entities.Student<span>;
</span><span>
</span><span>import </span>java.util.ArrayList<span>;
</span><span>import </span>java.util.List<span>;
</span><span>
</span><span>public class </span>DataInitializer {

    <span>public static void </span><span>AddSampleDataAsync</span>(<span>final </span>AppDatabase db) {
        PopulateSampleDbAsync task = <span>new </span>PopulateSampleDbAsync(db)<span>;
</span><span>        </span>task.execute()<span>;
</span><span>    </span>}

    <span>private static void </span><span>populateSampleData</span>(AppDatabase db) {
  <span>
</span><span>        </span>List&lt;Student&gt; studentList = <span>new </span>ArrayList&lt;&gt;()<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>21</span><span>, </span><span>10</span><span>, </span><span>"Student 1"</span><span>, </span><span>"Student Address 1"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>22</span><span>, </span><span>10</span><span>, </span><span>"Student 2"</span><span>, </span><span>"Student Address 2"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>23</span><span>, </span><span>10</span><span>, </span><span>"Student 3"</span><span>, </span><span>"Student Address 3"</span>))<span>;
</span><span>
</span><span>        </span>studentList.add(<span>new </span>Student(<span>24</span><span>, </span><span>11</span><span>, </span><span>"Student 4"</span><span>, </span><span>"Student Address 4"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>25</span><span>, </span><span>11</span><span>, </span><span>"Student 5"</span><span>, </span><span>"Student Address 5"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>26</span><span>, </span><span>11</span><span>, </span><span>"Student 6"</span><span>, </span><span>"Student Address 6"</span>))<span>;
</span><span>
</span><span>        </span>studentList.add(<span>new </span>Student(<span>27</span><span>, </span><span>12</span><span>, </span><span>"Student 7"</span><span>, </span><span>"Student Address 7"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>28</span><span>, </span><span>12</span><span>, </span><span>"Student 8"</span><span>, </span><span>"Student Address 8"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>29</span><span>, </span><span>12</span><span>, </span><span>"Student 9"</span><span>, </span><span>"Student Address 9"</span>))<span>;
</span><span>
</span><span>        </span>studentList.add(<span>new </span>Student(<span>30</span><span>, </span><span>13</span><span>, </span><span>"Student 10"</span><span>, </span><span>"Student Address 10"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>31</span><span>, </span><span>13</span><span>, </span><span>"Student 11"</span><span>, </span><span>"Student Address 11"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>32</span><span>, </span><span>13</span><span>, </span><span>"Student 12"</span><span>, </span><span>"Student Address 12"</span>))<span>;
</span><span>
</span><span>        </span>studentList.add(<span>new </span>Student(<span>30</span><span>, </span><span>14</span><span>, </span><span>"Student 13"</span><span>, </span><span>"Student Address 13"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>31</span><span>, </span><span>14</span><span>, </span><span>"Student 14"</span><span>, </span><span>"Student Address 14"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>32</span><span>, </span><span>14</span><span>, </span><span>"Student 15"</span><span>, </span><span>"Student Address 15"</span>))<span>;
</span><span>
</span><span>        </span>studentList.add(<span>new </span>Student(<span>30</span><span>, </span><span>15</span><span>, </span><span>"Student 16"</span><span>, </span><span>"Student Address 16"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>31</span><span>, </span><span>15</span><span>, </span><span>"Student 17"</span><span>, </span><span>"Student Address 17"</span>))<span>;
</span><span>        </span>studentList.add(<span>new </span>Student(<span>32</span><span>, </span><span>15</span><span>, </span><span>"Student 18"</span><span>, </span><span>"Student Address 18"</span>))<span>;
</span><span>        </span>db.studentDao().insertMultipleRecord(studentList)<span>;
</span><span>    </span>}

    <span>private static class </span>PopulateSampleDbAsync <span>extends </span>AsyncTask&lt;Void<span>, </span>Void<span>, </span>Void&gt; {

        <span>private final </span>AppDatabase <span>mDb</span><span>;
</span><span>
</span><span>        </span><span>PopulateSampleDbAsync</span>(AppDatabase db) {
            <span>mDb </span>= db<span>;
</span><span>        </span>}

        <span>@Override
</span><span>        </span><span>protected </span>Void <span>doInBackground</span>(<span>final </span>Void... params) {
            <span>populateSampleData</span>(<span>mDb</span>)<span>;
</span><span>            return null;
</span><span>        </span>}
    }
}</pre>
7) Create  an activity to create the database, insert dummy values
<pre><span>public class </span>MainActivity <span>extends </span>AppCompatActivity {
    <span>private </span>AppCompatTextView <span>mDataappCompatTextView</span><span>;</span><span>
</span><span>    </span><span>@Override
</span><span>    </span><span>protected void </span><span>onCreate</span>(Bundle savedInstanceState) {
        <span>super</span>.onCreate(savedInstanceState)<span>;
</span><span>        </span>setContentView(R.layout.<span>activity_main</span>)<span>;
</span><span>        </span>AppDatabase appDatabase = AppDatabase.<span>getDatabase</span>(MainActivity.<span>this</span>)<span>;
</span><span>        </span><span>//inserting sample data
</span><span>        </span>DataInitializer.<span>AddSampleDataAsync</span>(appDatabase)<span>;
</span><span>     </span><span>
</span><span>    </span>}
}

</pre>
<img src="http://thoughtnerds.com/wp-content/uploads/2018/02/Screenshot_20180218-141622-169x300.jpg" alt="" width="254" height="451" class="alignnone wp-image-552" />


Thank you.Happy Coding :D
