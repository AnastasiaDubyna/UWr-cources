import NavigationTab from "./NavigationTab";

const Navigation = () => {
    const tabs = [
        {
            name: "Subjects",
            id: "subjects-tab"
        },
        {
            name: "Tasks",
            id: "tasks-tab"
        },
        {
            name: "Grades",
            id: "grades-tab"
        },
        {
            name: "Schedule",
            id: "schedule-tab"
        }
    ]

    return (
        <div className="navigation-bar">
            <div className="left-box navbar-element"/>
            <>
                {tabs.map((tab) =>
                    <NavigationTab name={tab.name} key={tab.id}/>
                )}
            </>
            <div className="right-box navbar-element"/>
        </div>
    )
}

export default Navigation