function Tree(val, left, right) {
    this.left = left;
    this.right = right;
    this.val = val;
}

// Tree.prototype[Symbol.iterator] = function*() {
//     yield this.val;
//     if ( this.left ) yield* this.left;
//     if ( this.right ) yield* this.right;
// }

// //Kolejka
Tree.prototype[Symbol.iterator] = function* () {
    let queue = [];
    queue.splice(1, 0, this);
    while(queue.length !== 0){
        let firstNode = queue.splice(0, 1)[0];
        if (firstNode.right) {
            queue.splice(queue.length, 0, firstNode.right);
        }
        if (firstNode.left) {
            queue.splice(queue.length, 0, firstNode.left);
        }
        yield firstNode.val;
    }
}

//Stos
// Tree.prototype[Symbol.iterator] = function* () {
//     let queue = [];
//     queue.splice(0, 0, this);
//     while(queue.length !== 0){
//         let lastNode = queue.splice(queue.length - 1, 1)[0];
//         if (lastNode.right) {
//             queue.splice(queue.length, 0, lastNode.right);
//         }
//         if (lastNode.left) {
//             queue.splice(queue.length, 0, lastNode.left);
//         }
//         yield lastNode.val;
//     }
// }



var root = new Tree( 1, new Tree( 2, new Tree( 3, new Tree(4), new Tree(5) ) ), new Tree( 6 ));

for ( var e of root ) {
    console.log( e );
}
    //         1
    //        / \
    //       2   6
    //      /
    //     3
    //    / \
    //   4   5  