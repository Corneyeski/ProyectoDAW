var Imgs = [
    'http://tympanus.net/Development/GridLoadingEffects/images/1.jpg',
    'http://tympanus.net/Development/GridLoadingEffects/images/3.jpg',	'https://d13yacurqjgara.cloudfront.net/users/64706/screenshots/1167254/attachments/152315/SUGARSKULL-01.png',
    'http://tympanus.net/Development/GridLoadingEffects/images/8.jpg',
    'http://tympanus.net/Development/GridLoadingEffects/images/10.png',
    'http://tympanus.net/Development/GridLoadingEffects/images/14.png',
    'http://tympanus.net/Development/GridLoadingEffects/images/9.jpg',
    'http://tympanus.net/Development/GridLoadingEffects/images/13.png',
    'http://tympanus.net/Development/GridLoadingEffects/images/12.png',
    'http://tympanus.net/Development/GridLoadingEffects/images/4.jpg',	'http://www.thedrum.com/uploads/news/172673/DzrMPF_DeezerPoster_MusicSoundBetterWithYou_03.jpg'
];

$(document).ready(function(){
    $grid = $('#grid-content');

    $.fn.revealItems = function($items){

        var iso = this.data('isotope');
        var itemSelector = iso.options.itemSelector;
        $items.hide();
        $(this).append($items);
        $items.imagesLoaded().progress(function(imgLoad, image){
            var $item = $(image.img).parents(itemSelector);
            $item.show();
            iso.appended($item);
        });

        return this;
    }
    $grid.isotope({
        containerStyle: null,
        masonry:{
            columnWidth: 300,
            gutter: 15
        },
        itemSelector: '.grid-item',
        filter : '*',
        transitionDuration: '0.4s'
    });


    $grid.imagesLoaded().progress(function(){
        $grid.isotope();
    })

    /*$grid.imagesLoaded( function() {
     $grid.isotope({
     containerStyle: null,
     masonry:{
     columnWidth: 300,
     gutter: 15
     },
     itemSelector: '.grid-item',
     filter : '*',
     transitionDuration: '0.4s'
     });
     });*/

    function GenerateItems(){
        var items = '';
        for(var i=0; i < 20; i++){if (window.CP.shouldStopExecution(1)){break;}
            items += '<div class="grid-item c'+(i%9)+' wow fadeInUp" ><img src="'+Imgs[i%Imgs.length]+'" /></div>';
        }
        window.CP.exitedLoop(1);

        return $(items);
    }

    // SimpleInfiniteScroll
    function Infinite(e){
        if((e.type == 'scroll') || e.type == 'click'){
            var doc = document.documentElement;
            var top = (window.pageYOffset || doc.scrollTop) - (doc.clientTop || 0);
            var bottom = top + $(window).height();
            var docBottom = $(document).height();

            if(bottom + 50 >= docBottom){
                $grid.revealItems(GenerateItems());
            }
        }
    }

    $grid.revealItems(GenerateItems());

    $(document).on('click','.filter-item',function(){
        $('.filter-item.active').removeClass('active');
        $(this).addClass('active');
        var f = $(this).data('f');
        console.log(f);
        $grid.find('.grid-item').removeClass('wow').removeClass('fadeInUp');
        $grid.isotope({filter: f});

    })


    $(window).resize(function(){
        var margin=40;
        var padding=15;
        var columns=0;
        var cWidth=300;
        var windowWidth = $(window).width();

        var overflow = false;
        while(!overflow){if (window.CP.shouldStopExecution(2)){break;}
            columns++;
            var WidthTheory = ((cWidth*columns)+((columns+1)*padding)+margin);
            if(WidthTheory > windowWidth)
                overflow = true;
        }
        window.CP.exitedLoop(2);

        if(columns > 1)
            columns--;

        var GridWidth = ((cWidth*columns)+((columns+1)*padding)+margin);

        if( GridWidth != $('#grid').width()){
            $('#grid').width(GridWidth);
        }
    });
    $(window).scroll(Infinite);
    new WOW().init();

})
