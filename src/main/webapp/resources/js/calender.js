document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ ],
        initialView: 'dayGridMonth',
        themeSystem: 'bootstrap5',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,dayGridWeek'
        },
        dayMaxEvents: true,
        defaultView : 'listWeek',
        navLinks : true, // can click day/week names to nav
        eventLimit : true,
        // editable: true,
        events: [
            {
                title: 'All Day Event',
                start: '2024-04-21'
            },
            {
                title: 'Long Event',
                start: '2024-04-22',
                end: '2024-04-25'
            },
            {
                groupId: 999,
                title: 'Repeating Event',
                start: '2018-01-09T16:00:00'
            },
            {
                groupId: 999,
                title: 'Repeating Event',
                start: '2018-01-16T16:00:00'
            },
            {
                title: 'Conference',
                start: '2018-01-11',
                end: '2018-01-13'
            },
            {
                title: 'Meeting',
                start: '2018-01-12T10:30:00',
                end: '2018-01-12T12:30:00'
            },
            {
                title: 'Lunch',
                start: '2018-01-12T12:00:00'
            },
            {
                title: 'Meeting',
                start: '2018-01-12T14:30:00'
            },
            {
                title: 'Happy Hour',
                start: '2018-01-12T17:30:00'
            },
            {
                title: 'Dinner',
                start: '2018-01-12T20:00:00'
            },
            {
                title: 'Birthday Party',
                start: '2018-01-13T07:00:00'
            },
            {
                title: 'Click for Google',
                url: 'http://google.com/',
                start: '2018-01-28'
            }
        ]
    });
    calendar.render();
});