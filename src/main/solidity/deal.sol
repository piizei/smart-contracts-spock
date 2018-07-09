pragma solidity ^0.4.20;

contract Deal {

    function Deal() public {
        seller = msg.sender;
    }
    function kill() onlySeller public {
        selfdestruct(seller);
    }

    address public seller;
    address public buyer;
    bytes32 public dealHash;

    modifier onlySeller {
        if (msg.sender != seller) revert();
        _;
    }

    modifier notSeller {
        if (msg.sender == seller) revert();
        _;
    }

    function createDeal(bytes32 _dealHash) public onlySeller {
        dealHash = _dealHash;
    }

    function signDeal() public notSeller {
        if (buyer == address(0)) {
            buyer = msg.sender;
        }
    }


}